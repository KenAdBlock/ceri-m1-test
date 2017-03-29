package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.mockito.Mockito.when;

/**
 * Created by Kenny on 29/03/2017.
 */
public class IPokedexTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private static IPokedex pokedex;

    private static Pokemon bulbizarre = new Pokemon(
            0,
            "Bulbizarre",
            126,
            126,
            90,
            613,
            64,
            4000,
            4,
            56
    );

    private static Pokemon aquali = new Pokemon(
            133,
            "Aquali",
            186,
            168,
            260,
            2729,
            202,
            5000,
            4,
            100
    );

    private static Comparator<Pokemon> comp1 = Comparator.comparing(PokemonMetadata::getName);
    private static Comparator<Pokemon> comp2 = Comparator.comparing(PokemonMetadata::getAttack);

    @Before
    public void setUp() throws PokedexException {
        MockitoAnnotations.initMocks(this);
        when(pokedex.size()).thenReturn(151);
        when(pokedex.getPokemon(0)).thenReturn(bulbizarre);
        when(pokedex.getPokemon(200)).thenThrow(new PokedexException("Invalid index"));
        List<Pokemon> list1 = new ArrayList<>(), list2 = new ArrayList<>();
        list1.add(bulbizarre);
        list1.add(aquali);
        // get 151 pokemons in the list
        for(int i = 2; i < 151; i++) {
            list1.add(new Pokemon(-10, "MISSINGNO", 0, 0, 0, 0, 0, 0, 0, 0));
        }
        when(pokedex.getPokemons()).thenReturn(Collections.unmodifiableList(list1));
        list2.add(aquali);
        list2.add(bulbizarre);
        when(pokedex.getPokemons(comp1)).thenReturn(Collections.unmodifiableList(list2));
        when(pokedex.getPokemons(comp2)).thenReturn(Collections.unmodifiableList(list1));
    }

    @Test
    public void testSize() {
        assertEquals(151, pokedex.size());
    }

    @Test
    public void testAddPokemon() {
        assertEquals(0, pokedex.addPokemon(bulbizarre));
    }

    @Test
    public void testGetPokemon() throws PokedexException {
        pokedex.addPokemon(bulbizarre);

        assertEquals("Bulbizarre", pokedex.getPokemon(0).getName());

        try {
            pokedex.getPokemon(200);
            fail("Expected a PokedexException to be thrown");
        } catch(PokedexException e) {
            assertEquals("Invalid index", e.getMessage());
        }
    }

    @Test
    public void testGetPokemons() throws PokedexException {
        List<Pokemon> list = pokedex.getPokemons();

        assertEquals(pokedex.size(), list.size());
        assertEquals(pokedex.getPokemon(0).getName(), list.get(0).getName());

        try{
            list.add(bulbizarre);
            fail("Expected UnsupportedOperationException to be thrown");
        } catch (UnsupportedOperationException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testGetPokemonsWithOrder() throws PokedexException {
        List<Pokemon> listWithoutOrder = pokedex.getPokemons();
        List<Pokemon> listOrderedWithName = pokedex.getPokemons(comp1);
        List<Pokemon> listOrderedWithAttack = pokedex.getPokemons(comp2);

        assertTrue(listWithoutOrder.indexOf(aquali) >= listOrderedWithName.indexOf(aquali));
        assertTrue(listOrderedWithName.indexOf(aquali) <= listOrderedWithAttack.indexOf(aquali));
    }
}

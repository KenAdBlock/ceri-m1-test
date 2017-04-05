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
import java.util.List;

import static junit.framework.TestCase.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by Kenny on 29/03/2017.
 */
public class IPokedexTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    protected static IPokedex pokedex;

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

    private static final int[] pokedexSize = {0};

    @Before
    public void setUp() throws PokedexException {
        MockitoAnnotations.initMocks(this);
        pokedexSize[0] = 0;
        List<Pokemon> list1 = new ArrayList<>(), list2 = new ArrayList<>();

        when(pokedex.size()).thenAnswer(i -> pokedexSize[0]);
        when(pokedex.addPokemon(any())).then(i -> pokedexSize[0]++);
        when(pokedex.getPokemon(0)).thenReturn(bulbizarre);
        when(pokedex.getPokemon(1)).thenThrow(new PokedexException("Invalid index"));

        list1.add(bulbizarre);
        list1.add(aquali);

        when(pokedex.getPokemons()).thenReturn(Collections.unmodifiableList(list1));

        list2.add(aquali);
        list2.add(bulbizarre);

        when(pokedex.getPokemons(any())).thenReturn(Collections.unmodifiableList(list2)).thenReturn(Collections.unmodifiableList(list1));
    }

    @Test
    public void testSize() {
        assertEquals(0, pokedex.size());
    }

    @Test
    public void testAddPokemon() {
        assertEquals(0, pokedex.addPokemon(bulbizarre));
        assertEquals(1, pokedex.size());

        assertEquals(1, pokedex.addPokemon(aquali));
        assertEquals(2, pokedex.size());
    }

    @Test
    public void testGetPokemon() throws PokedexException {
        pokedex.addPokemon(bulbizarre);

        assertEquals("Bulbizarre", pokedex.getPokemon(0).getName());
    }

    @Test
    public void testGetPokemons() throws PokedexException {
        pokedex.addPokemon(bulbizarre);
        pokedex.addPokemon(aquali);

        List<Pokemon> listPokemon = pokedex.getPokemons();

        assertEquals(pokedex.size(), listPokemon.size());
        assertEquals(pokedex.getPokemon(0).getName(), listPokemon.get(0).getName());
    }

    @Test
    public void testGetPokemonsWithOrder() throws PokedexException {
        pokedex.addPokemon(bulbizarre);
        pokedex.addPokemon(aquali);

        List<Pokemon> listPokemonOrderedWithName = pokedex.getPokemons(PokemonComparators.NAME);
        List<Pokemon> listPokemonOrderedWithIndex = pokedex.getPokemons(PokemonComparators.INDEX);

        assertEquals(0, listPokemonOrderedWithName.indexOf(aquali));
        assertEquals(1, listPokemonOrderedWithIndex.indexOf(aquali));
    }

}
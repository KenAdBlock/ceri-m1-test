package fr.univavignon.pokedex.api;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static junit.framework.TestCase.assertEquals;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

public class IPokedexTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private static IPokedex pokedex;

    private List<Pokemon> pokemonsListName = new ArrayList<>();

    private List<Pokemon> pokemonsListAttack = new ArrayList<>();

    private static Comparator<Pokemon> compareName;

    private static Comparator<Pokemon> compareAttack;

    private static Pokemon aquali;

    private static Pokemon bulbizarre;



    @Before
    public void setUp() throws PokedexException {

        // Initialize pokemon Aquali
        aquali = new Pokemon(
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


        // Initialize pokemon Bulbizarre
        bulbizarre = new Pokemon(
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


        // Initialize Mock
        MockitoAnnotations.initMocks(this);

        // Define pokedex size
        when(pokedex.size()).thenReturn(151);

        // add test pokemon
        when(pokedex.getPokemon(0)).thenReturn(bulbizarre);

        // define overflow pokedex index
        when(pokedex.getPokemon(160)).thenThrow(new PokedexException("Invalid index"));


        // Fill pokedex with ghosts pokemons
        populatePokedexList();

        // set pokemon default list
        when(pokedex.getPokemons()).thenReturn(Collections.unmodifiableList(pokemonsListName));


        // Initialize comparators
        compareName = Comparator.comparing(PokemonMetadata::getName);
        compareAttack = Comparator.comparing(PokemonMetadata::getAttack);

        when(pokedex.getPokemons(PokemonComparators.INDEX)).thenAnswer(a -> Arrays.asList(new Pokemon[] {bulbizarre}));

        // set pokemon list Name
        when(pokedex.getPokemons(compareName)).thenReturn(Collections.unmodifiableList(pokemonsListName));

        // set pokemon list Attack
        when(pokedex.getPokemons(compareAttack)).thenReturn(Collections.unmodifiableList(pokemonsListAttack));

    }


    private void populatePokedexList() {

        pokemonsListName.add(bulbizarre);
        pokemonsListName.add(aquali);

        // get 151 pokemons in the list
        for(int i = 2; i < 151; i++) {

            Pokemon ghost = new Pokemon(
                    i,
                    "Ghost",
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0
            );

            pokemonsListName.add(ghost);
        }


        pokemonsListAttack.add(aquali);
        pokemonsListAttack.add(bulbizarre);

    }


    @Test
    public void testSize() {
        assertEquals(151, pokedex.size());
    }

    @Test
    public void testAddPokemon() {
        assertEquals(0, pokedex.addPokemon(bulbizarre));
    }


    @Test(expected=PokedexException.class)
    public void testGetPokemon() throws PokedexException {
        pokedex.getPokemon(160);
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
            e.getMessage();
        }
    }

    @Test
    public void testGetPokemonsWithOrder() throws PokedexException {

        Assert.assertEquals("Bulbizarre", pokedex.getPokemons(PokemonComparators.INDEX).get(0).getName());

        Assert.assertEquals(pokemonsListName, pokedex.getPokemons(compareName));

        Assert.assertEquals(pokemonsListAttack, pokedex.getPokemons(compareAttack));


    }

}
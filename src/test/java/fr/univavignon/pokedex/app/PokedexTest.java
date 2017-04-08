package fr.univavignon.pokedex.app;

import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.Pokemon;
import fr.univavignon.pokedex.api.PokemonComparators;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by jonathan on 07/04/17.
 */
public class PokedexTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private static Pokedex pokedex;

    private Pokemon pokemon;


    @Before
    public void setUp() {

        PokemonMetadataProvider pokemonMetadataProvider = new PokemonMetadataProvider();
        PokemonFactory pokemonFactory = new PokemonFactory();
        pokedex = new Pokedex(pokemonMetadataProvider, pokemonFactory);

        pokemon = pokemonFactory.createPokemon(42, 123, 97, 1000, 41);


    }

    @Test
    public void testSize() {

        assertEquals(0, pokedex.size());
        assertNotNull(pokedex);

    }

    @Test
    public void testAddPokemon() {

        pokedex.addPokemon(pokemon);

        assertEquals(1, pokedex.size());

    }

    @Test
    public void testGetPokemon() {

        pokedex.addPokemon(pokemon);

        try {
            assertEquals(42, pokedex.getPokemon(0).getIndex());
        } catch (PokedexException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void testGetPokemons() {

        pokedex.addPokemon(pokemon);

        List<Pokemon> list = pokedex.getPokemons();

        TestCase.assertEquals(pokedex.size(), list.size());

        try {
            TestCase.assertEquals(pokedex.getPokemon(0).getName(), list.get(0).getName());
        } catch (PokedexException e) {
            e.printStackTrace();
        }

    }


    @Test  (expected=java.lang.UnsupportedOperationException.class)
    public void testGetPokemonsModify() {

        List<Pokemon> list = pokedex.getPokemons();

        list.add(pokemon);

    }


    @Test
    public void testCreatePokemon() {

        Pokemon testPokemon = pokedex.createPokemon(42, 123, 97, 1000, 41);

        assertEquals(pokemon instanceof Pokemon, testPokemon instanceof Pokemon);

        assertEquals(pokemon.getIndex(), testPokemon.getIndex());

    }


    @Test
    public void testGetPokemonsOrdered() {

        pokedex.addPokemon(pokemon);

        Pokemon bulbizarre = pokedex.createPokemon(
                1,
                126,
                126,
                90,
                613
        );

        pokedex.addPokemon(bulbizarre);

        assertEquals("Bulbasaur", pokedex.getPokemons(PokemonComparators.NAME).get(0).getName());
        assertEquals(123, pokedex.getPokemons(PokemonComparators.CP).get(0).getCp());
        assertEquals(1, pokedex.getPokemons(PokemonComparators.INDEX).get(0).getIndex());

    }


}

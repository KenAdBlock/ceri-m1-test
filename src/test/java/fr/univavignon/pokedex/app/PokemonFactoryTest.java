package fr.univavignon.pokedex.app;

import fr.univavignon.pokedex.api.Pokemon;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;

/**
 * Created by jonathan on 07/04/17.
 */
public class PokemonFactoryTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private static PokemonFactory pokemonFactory;


    @Before
    public void setUp() {

        pokemonFactory = new PokemonFactory();

    }


    @Test
    public void testCreatePokemon() {

        Pokemon pokemon = pokemonFactory.createPokemon(42, 123, 97, 1000, 41);

        assertEquals(42, pokemon.getIndex());

        assertEquals(123, pokemon.getCp());

        assertEquals(97, pokemon.getHp());

        assertEquals(1000, pokemon.getDust());

        assertEquals(41, pokemon.getCandy());


    }


    @Test
    public void testcomputeIV() {

        Pokemon pokemon = pokemonFactory.createPokemon(149, 3280, 149, 9000, 41);

        double DELTA = 1e-2;

        assertEquals(0.73, pokemon.getIv(), DELTA);
    }




}

package fr.univavignon.pokedex.app;

import fr.univavignon.pokedex.api.*;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

/**
 * Created by jonathan on 07/04/17.
 */
public class PokemonTrainerFactoryTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private static PokemonTrainerFactory pokemonTrainerFactory;

    private IPokedexFactory pokedexFactory;


    @Before
    public void setUp() {

        pokemonTrainerFactory = new PokemonTrainerFactory();

        pokedexFactory = new PokedexFactory();

    }


    @Test
    public void testGetTrainer() {

        PokemonTrainer pokemonTrainer = pokemonTrainerFactory.createTrainer("John");

        assertNotNull(pokemonTrainer);
    }

    @Test
    public void testCreateTrainer() {

        PokemonTrainer pokemonTrainer = pokemonTrainerFactory.createTrainer("John", Team.VALOR, pokedexFactory);

        assertNotNull(pokemonTrainer);

        Pokemon pokemon = pokemonTrainer.getPokedex().createPokemon(12, 456, 321, 1200, 12);
        pokemonTrainer.getPokedex().addPokemon(pokemon);

        try {
            pokemonTrainerFactory.saveData(pokemonTrainer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PokedexException e) {
            e.printStackTrace();
        }

        TestCase.assertEquals("John", pokemonTrainer.getName());

        TestCase.assertEquals(Team.VALOR, pokemonTrainer.getTeam());

        assertNotNull(pokemonTrainer.getPokedex());

        //TestCase.assertEquals(1, pokemonTrainer.getPokedex().size());
    }


}

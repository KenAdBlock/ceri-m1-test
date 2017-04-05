package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Kenny on 05/04/2017.
 */
public class IPokemonTrainerFactoryTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private static IPokemonTrainerFactory pokemonTrainerFactory;

    @Mock
    private static IPokedexFactory pokedexFactory;

    @Before
    public void setUp() throws PokedexException {
        MockitoAnnotations.initMocks(this);
        IPokedex mockPokedex = mock(IPokedex.class);

        when(mockPokedex.size()).thenReturn(0);
        when(pokemonTrainerFactory.createTrainer("Kenny", Team.VALOR, pokedexFactory)).thenReturn(new PokemonTrainer(
            "Kenny", Team.VALOR, mockPokedex
        ));
    }

    @Test
    public void testCreateTrainer() {
        PokemonTrainer pokemonTrainer = pokemonTrainerFactory.createTrainer("Kenny", Team.VALOR, pokedexFactory);

        assertNotNull(pokemonTrainer);
        assertEquals("Kenny", pokemonTrainer.getName());
        assertEquals(Team.VALOR, pokemonTrainer.getTeam());
        assertNotNull(pokemonTrainer.getPokedex());
        assertEquals(0, pokemonTrainer.getPokedex().size());
    }
}

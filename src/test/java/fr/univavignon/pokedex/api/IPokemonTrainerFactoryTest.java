package fr.univavignon.pokedex.api;

import fr.univavignon.pokedex.app.Pokedex;
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

        Pokedex mockPokedex = mock(Pokedex.class);

        when(mockPokedex.size()).thenReturn(151);

        when(pokemonTrainerFactory.createTrainer("John", Team.VALOR, pokedexFactory))
                .thenReturn(new PokemonTrainer("John", Team.VALOR, mockPokedex));
    }

    @Test
    public void testCreateTrainer() {
        PokemonTrainer trainer = pokemonTrainerFactory.createTrainer("John", Team.VALOR, pokedexFactory);

        assertNotNull(trainer);

        assertEquals("John", trainer.getName());

        assertEquals(Team.VALOR, trainer.getTeam());

        assertNotNull(trainer.getPokedex());

        assertEquals(151, trainer.getPokedex().size());
    }


}
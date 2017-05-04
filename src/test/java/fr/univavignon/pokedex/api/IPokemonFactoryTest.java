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
import static org.mockito.Mockito.when;

public class IPokemonFactoryTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private static IPokemonFactory pokemonFactory;

    @Before
    public void setUp() throws PokedexException {
        MockitoAnnotations.initMocks(this);

        Pokemon bulbizarre = new Pokemon(
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

        when(pokemonFactory.createPokemon(0, 613, 64, 4000, 4))
                .thenReturn(bulbizarre);
    }

    @Test
    public void testCreatePokemon() {
        Pokemon pokemon = pokemonFactory.createPokemon(0, 613, 64, 4000, 4);
        assertNotNull(pokemon);
        assertEquals("Bulbizarre", pokemon.getName());
    }

}
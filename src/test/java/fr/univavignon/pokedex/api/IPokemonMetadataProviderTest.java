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

public class IPokemonMetadataProviderTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private static IPokemonMetadataProvider pokemonMetadataProvider;

    @Before
    public void setUp() throws PokedexException {
        MockitoAnnotations.initMocks(this);

        PokemonMetadata aquali = new PokemonMetadata(
                0,
                "Aquali",
                512,
                256,
                100
        );

        when(pokemonMetadataProvider.getPokemonMetadata(0))
                .thenReturn(aquali);
    }

    @Test
    public void testGetPokemonMetadata() throws PokedexException {

        PokemonMetadata metadata = pokemonMetadataProvider.getPokemonMetadata(0);

        assertNotNull(metadata);

        assertEquals("Aquali", metadata.getName());

        assertEquals(512, metadata.getAttack());

        assertEquals(256, metadata.getDefense());

        assertEquals(100, metadata.getStamina());
    }

}
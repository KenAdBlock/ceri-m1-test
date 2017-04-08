package fr.univavignon.pokedex.app;

import fr.univavignon.pokedex.api.IPokedex;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.PokemonMetadata;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by jonathan on 07/04/17.
 */
public class PokemonMetadataProviderTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private static PokemonMetadataProvider pokemonMetadataProvider;



    @Before
    public void setUp() {

        pokemonMetadataProvider = new PokemonMetadataProvider();

    }

    @Test
    public void testGetPokemonMetadata() throws PokedexException {

        PokemonMetadata metadata = pokemonMetadataProvider.getPokemonMetadata(42);

        assertEquals(42, metadata.getIndex());
    }


    @Test (expected=PokedexException.class)
    public void testGetPokemonMetadataErrorMin() throws PokedexException {

        PokemonMetadata metadata = pokemonMetadataProvider.getPokemonMetadata(0);

    }


    @Test (expected=PokedexException.class)
    public void testGetPokemonMetadataErrorMax() throws PokedexException {

        PokemonMetadata metadata = pokemonMetadataProvider.getPokemonMetadata(151);

    }

}

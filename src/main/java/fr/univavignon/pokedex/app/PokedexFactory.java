package fr.univavignon.pokedex.app;

import fr.univavignon.pokedex.api.IPokedex;
import fr.univavignon.pokedex.api.IPokedexFactory;
import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.IPokemonMetadataProvider;

/**
 * Created by jonathan on 06/04/17.
 */
public class PokedexFactory implements IPokedexFactory {


    @Override
    public IPokedex createPokedex(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {

        Pokedex pokedex = new Pokedex(metadataProvider, pokemonFactory);

        return pokedex;
    }


}

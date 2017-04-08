package fr.univavignon.pokedex.app;

import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.Pokemon;
import fr.univavignon.pokedex.api.PokemonMetadata;

/**
 * Created by jonathan on 06/04/17.
 */
public class PokemonFactory implements IPokemonFactory {

    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {

        PokemonMetadataProvider metadataProvider = new PokemonMetadataProvider();

        Pokemon pokemon = null;

        try {

            PokemonMetadata metadata = metadataProvider.getPokemonMetadata(index);

            double iv = metadataProvider.calcIv();

            pokemon = new Pokemon(
                    index,
                    metadata.getName(),
                    metadata.getAttack(),
                    metadata.getDefense(),
                    metadata.getStamina(),
                    cp,
                    hp,
                    dust,
                    candy,
                    iv
            );

        } catch (PokedexException e) {
            e.printStackTrace();
        }




        return pokemon;
    }

}

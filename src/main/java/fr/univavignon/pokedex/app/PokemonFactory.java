package fr.univavignon.pokedex.app;

import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.Pokemon;
import fr.univavignon.pokedex.api.PokemonMetadata;
import fr.univavignon.pokedex.tools.Curl;

import java.io.IOException;

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

            double iv = this.computeIV(
                    metadata.getName(),
                    cp,
                    hp,
                    dust,
                    false
            );

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


    /**
     * Compute the IV for a given pokemon
     * @param name
     * @param cp
     * @param hp
     * @param dust
     * @param levelUp
     * @return
     */
    private double computeIV(String name, int cp, int hp, int dust, boolean levelUp) {

        double iv = 0;

        String ivapi = "http://hoomies.fr/pokeiv/?";

        String params = "name=" + name
                + "&cp=" + cp
                + "&hp=" + hp
                + "&dust=" + dust
                + "&levelUp=" + levelUp;

        String link = ivapi + params;

        try {
            iv = Double.parseDouble(Curl.curl(link));
        } catch (IOException e) {
            e.printStackTrace();
        }


        return iv;
    }

}

package fr.univavignon.pokedex.app;

import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.Pokemon;
import fr.univavignon.pokedex.api.PokemonMetadata;

/**
 * Created by jonathan on 05/04/17.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Pokemon");


        PokedexFactory pokedexFactory = new PokedexFactory();
        PokemonMetadataProvider pokemonMetadataProvider = new PokemonMetadataProvider();
        PokemonFactory pokemonFactory = new PokemonFactory();

        Pokedex pokedex = new Pokedex(pokemonMetadataProvider, pokemonFactory);

        Pokemon pokemon42 = pokedex.createPokemon(42, 456, 123, 1500, 23);

        int id = pokedex.addPokemon(pokemon42);

        try {
            System.out.println(pokedex.getPokemon(id).getName());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}

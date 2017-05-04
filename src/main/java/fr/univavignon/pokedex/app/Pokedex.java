package fr.univavignon.pokedex.app;

import fr.univavignon.pokedex.api.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by jonathan on 06/04/17.
 */
public class Pokedex implements IPokedex {

    private static final int maxPokemons = 150;

    private PokemonMetadataProvider pokemonMetadataProvider;

    private PokemonFactory pokemonFactory;

    private List<Pokemon> pokemons;


    public Pokedex(IPokemonMetadataProvider pokemonMetadataProvider, IPokemonFactory pokemonFactory) {
        this.pokemonMetadataProvider = (PokemonMetadataProvider) pokemonMetadataProvider;
        this.pokemonFactory = (PokemonFactory) pokemonFactory;
        this.pokemons = new ArrayList<Pokemon>();
    }


    @Override
    public int size() {
        return pokemons.size();
    }

    @Override
    public int addPokemon(Pokemon pokemon) {

        pokemons.add(pokemon);

        return pokemons.indexOf(pokemon);
    }

    @Override
    public Pokemon getPokemon(int id) throws PokedexException {

        if (id < 0 || id > maxPokemons - 1) {
            throw new PokedexException("Id is not valid !");
        }

        Pokemon pokemon = pokemons.get(id);

        return pokemon;
    }

    @Override
    public List<Pokemon> getPokemons() {
        return Collections.unmodifiableList(pokemons);
    }

    @Override
    public List<Pokemon> getPokemons(Comparator<Pokemon> order) {

        pokemons.sort(order);

        return Collections.unmodifiableList(pokemons);
    }

    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {

        Pokemon pokemon = pokemonFactory.createPokemon(
                index,
                cp,
                hp,
                dust,
                candy
        );

        return pokemon;
    }

    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        return pokemonMetadataProvider.getPokemonMetadata(index);
    }
}

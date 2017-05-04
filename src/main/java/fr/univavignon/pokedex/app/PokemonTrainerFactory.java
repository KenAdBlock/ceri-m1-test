package fr.univavignon.pokedex.app;

import com.google.gson.Gson;
import fr.univavignon.pokedex.api.*;
import fr.univavignon.pokedex.tools.IGSerializer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Created by jonathan on 08/04/17.
 */
public class PokemonTrainerFactory implements IPokemonTrainerFactory, IGSerializer {

    private String path;

    public PokemonTrainerFactory() {
        this.path = ".pokedex42/data/trainers/";
        this.initPath(path);
    }


    public PokemonTrainer createTrainer(String name) {

        PokemonTrainer pokemonTrainer = null;

        try {

            if (checkFile(path, name)) {
                System.out.println("Loading " + name + "...");
                pokemonTrainer = (PokemonTrainer) this.loadData(name);
            }

        } catch (Exception e) {
            e.getMessage();
        }


        return pokemonTrainer;
    }

    @Override
    public PokemonTrainer createTrainer(String name, Team team, IPokedexFactory pokedexFactory) {

        PokemonMetadataProvider metadataProvider = new PokemonMetadataProvider();

        PokemonFactory pokemonFactory = new PokemonFactory();

        Pokedex pokedex = (Pokedex) pokedexFactory.createPokedex(metadataProvider, pokemonFactory);

        PokemonTrainer pokemonTrainer = null;

        try {

            if (checkFile(path, name)) {
                System.out.println("Loading " + name + "...");
                pokemonTrainer = (PokemonTrainer) this.loadData(name);
            } else {
                System.out.println("Saving " + name + "...");
                pokemonTrainer = new PokemonTrainer(name, team, pokedex);
                this.saveData(pokemonTrainer);
            }

        } catch (Exception e) {
            e.getMessage();
        }


        return pokemonTrainer;
    }

    @Override
    public void saveData(Object object) throws IOException, PokedexException {

        PokemonTrainer pokemonTrainer = (PokemonTrainer) object;

        if (pokemonTrainer == null) {
            throw new PokedexException("Couldn't save empty trainer !");
        }

        if (path == null) {
            throw new PokedexException("Trainer path is not defined !");
        }

        String filename = this.getFileName(path, pokemonTrainer.getName());

        this.persistData(filename, pokemonTrainer);
    }

    @Override
    public Object loadData(String name) throws FileNotFoundException, PokedexException {

        if (path == null) {
            throw new PokedexException("Trainer path is not defined !");
        }

        PokemonTrainer pokemonTrainer = null;

        String filename = this.getFileName(path, name);

        try (Reader reader = new FileReader(filename)) {

            Gson gson = new Gson();

            pokemonTrainer = gson.fromJson(reader, PokemonTrainer.class);

            System.out.println("trainer: " + pokemonTrainer.getName());

            if (pokemonTrainer == null) {
                throw new PokedexException("Error loading Trainer !");
            }

            reader.close();
        } catch (IOException e) {
            e.getMessage();
        }


        return pokemonTrainer;
    }

}

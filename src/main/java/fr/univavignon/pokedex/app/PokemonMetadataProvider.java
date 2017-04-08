package fr.univavignon.pokedex.app;

import com.google.gson.Gson;
import fr.univavignon.pokedex.api.IPokemonMetadataProvider;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.Pokemon;
import fr.univavignon.pokedex.api.PokemonMetadata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by jonathan on 05/04/17.
 */
public class PokemonMetadataProvider implements IPokemonMetadataProvider {

    private String API = "http://hoomies.fr/pokemeta/?id=";


    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {

        if(index <= 0 || index > 150) {
            throw new PokedexException("Id is not valid !");
        }


        String link =  API + index;

        PokemonMetadata metadata  = null;

        try {

            String content = this.curl(link);

            metadata = this.Json2PokemonMetadata(content);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return metadata;
    }


    /**
     *
     * @return
     */
    public double calcIv() {

        double iv = 0;

        // Todo: calculate IV

        return iv;
    }


    /**
     * Get metadata infos into json from API
     * @param link
     * @return
     * @throws IOException
     */
    private String curl(String link) throws IOException {

        URL url = new URL(link);

        String content = "";

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
            for (String line; (line = reader.readLine()) != null;) {
                content += line;
            }
        } catch (IOException e) {
            e.getMessage();
        }


        return content;
    }


    /**
     * Create PokemonMetadata from Json
     * @param content
     * @return pokemonMetadata
     */
    private PokemonMetadata Json2PokemonMetadata(String content) {

        Gson g = new Gson();

        PokemonMetadata pokemonMetadata = g.fromJson(content, PokemonMetadata.class);

        return pokemonMetadata;
    }



}

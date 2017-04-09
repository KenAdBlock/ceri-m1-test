package fr.univavignon.pokedex.tools;

import com.google.gson.Gson;
import fr.univavignon.pokedex.api.PokedexException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Gson Manager interface to save and load Data
 * 
 * @author jonathan
 */
public interface IGSerializer {

    /**
     * Home path
     */
    String rootPath = System.getProperty("user.home") + "/";


    /**
     * Create folders if doesn't exists
     * @param dirname
     */
    default void initPath(String dirname) {
        File recordsDir = new File(rootPath, dirname);

        if (! recordsDir.exists()) {
            recordsDir.mkdirs();
        }
    }

    /**
     * Return full path filename
     * @param path
     * @param name
     * @return String
     */
    default String getFileName( String path, String name) {
        return rootPath  + path + name.toLowerCase() + ".json";
    }

    /**
     * Check if data file exists
     *
     * @param path
     * @param name
     * @return
     */
    default Boolean checkFile(String path, String name) {

        File f = new File(this.getFileName(path, name));

        return f.exists() && !f.isDirectory();
    }

    /**
     * Try to save Data into json file
     * @param object
     * @throws IOException
     * @throws PokedexException
     */
    void saveData(Object object) throws IOException, PokedexException;


    /**
     * Try to load Data from json file
     * @param name
     * @throws FileNotFoundException
     */
    Object loadData(String name) throws FileNotFoundException, PokedexException;


    /**
     * Convert Objet into json file
     * @param filename
     * @param object
     */
    default void persistData(String filename, Object object) {

        try (FileWriter writer = new FileWriter(filename)) {

            Gson gson = new Gson();

            gson.toJson(object, writer);

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
	
}

package fr.univavignon.pokedex.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by jonathan on 04/05/17.
 */
public class Curl {

    /**
     * Get metadata infos into json string from API
     *
     * @param link
     * @return
     * @throws IOException
     */
    public static String curl(String link) throws IOException {

        URL url = new URL(link);

        String content = "";

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
            for (String line; (line = reader.readLine()) != null; ) {
                content += line;
            }
        } catch (IOException e) {
            e.getMessage();
        }


        return content;
    }


}

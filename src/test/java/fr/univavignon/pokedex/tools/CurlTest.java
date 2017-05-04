package fr.univavignon.pokedex.tools;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by jonathan on 09/04/17.
 */
public class CurlTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();


    @Test
    public void testCurl() {

        try {
            assertEquals("0.73", Curl.curl("http://hoomies.fr/pokeiv/?name=Dragonite&cp=3280&hp=149&dust=9000&levelUp=0"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

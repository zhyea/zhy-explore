package org.chobit.core;

import org.junit.Test;

import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author robin
 */
public class ReusableMethodsTest {

    @Test
    public void rawToJson() throws URISyntaxException {
        URL url = ReusableMethods.class.getClassLoader().getResource("/a.json");
        System.out.println(url.toURI());
        System.out.println(url.getPath());
    }

}

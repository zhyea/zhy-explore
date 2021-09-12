package org.chobit.core;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.Test;

import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author robin
 */
public class ReusableMethodsTest {

    @Test
    public void rawToJson() throws URISyntaxException {
        URL url = ReusableMethods.class.getClassLoader().getResource("a.json");
        if (null == url) {
            System.out.println("the url is empty");
            return;
        }
        String path = url.getPath();
        String json = ReusableMethods.rawToJson(path);
        System.out.println(json);
    }


    @Test
    public void read(){
        InputStream in = ReusableMethods.class.getClassLoader().getResourceAsStream("a.json");
        DocumentContext ctx = JsonPath.parse(in);
        JsonPath jp = JsonPath.compile("$.store.book");
        Object obj = ctx.read(jp);
        System.out.println(obj);
    }





}

package org.chobit.core;

import com.jayway.jsonpath.JsonPath;

/**
 * @author robin
 */
public class ReusableMethods {



    public static String rawToJson(String pathToRawFile) {
        return TxtReader.read(pathToRawFile);
    }


    public static String readJson(String jsonPath, String pathToRawFile){
        String json = rawToJson(pathToRawFile);
        JsonPath jp = JsonPath.compile(jsonPath);
        return jp.read(json);
    }


}

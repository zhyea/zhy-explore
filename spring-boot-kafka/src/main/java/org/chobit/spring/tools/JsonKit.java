package org.chobit.spring.tools;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

public abstract class JsonKit {

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);

        mapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_ABSENT);

        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }


    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static <T> String toJson(T src) {
        try {
            return mapper.writeValueAsString(src);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public JsonKit() {
        throw new UnsupportedOperationException("Private constructor, cannot be accessed.");
    }
}

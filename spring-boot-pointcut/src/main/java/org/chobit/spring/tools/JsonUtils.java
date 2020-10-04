package org.chobit.spring.tools;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;

public abstract class JsonUtils {


    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
    }


    public static String toJson(Object source) {
        try {
            return mapper.writeValueAsString(source);
        } catch (JsonProcessingException e) {
            logger.error("Write to json error.", e);
        }
        return null;
    }


    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            logger.error("Read from json error.", e);
        }
        return null;
    }


    public static <T> T fromJson(String json, TypeReference<T> tr) {
        try {
            return mapper.readValue(json, tr);
        } catch (IOException e) {
            logger.error("Read from json error.", e);
        }
        return null;
    }


    private JsonUtils() {
    }

}

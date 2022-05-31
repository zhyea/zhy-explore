package org.chobit.core.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Json处理工具类
 *
 * @author robin
 */
public final class JsonKit {


    private static final Logger logger = LoggerFactory.getLogger(JsonKit.class);

    private static final ObjectMapper MAPPER = new ObjectMapper();


    static {
        MAPPER.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        MAPPER.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
        MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

    }


    public static ObjectMapper mapper() {
        return MAPPER;
    }


    /**
     * 将对象转为Json
     *
     * @param src 源对象
     * @param <T> 对象类型
     * @return 源对象对应的Json
     */
    public static <T> String toJson(T src) {
        try {
            return MAPPER.writeValueAsString(src);
        } catch (Exception e) {
            logger.error("Parsing src object to json string failed.", e);
            return null;
        }
    }


    /**
     * 将json字符串转为目标类型的实例
     *
     * @param json  json字符串
     * @param clazz 目标对象类型
     * @param <T>   目标对象类型泛型标记
     * @return json字符串对应的实例
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return MAPPER.readValue(json, clazz);
        } catch (Exception e) {
            logger.error("Read from json error, json: {}", json, e);
        }
        return null;
    }


    /**
     * 将json字符串转为目标类型的实例
     *
     * @param json json字符串
     * @param tr   类型包装
     * @param <T>  目标对象类型泛型标记
     * @return json字符串对应的实例
     */
    public static <T> T fromJson(String json, TypeReference<T> tr) {
        try {
            return MAPPER.readValue(json, tr);
        } catch (Exception e) {
            logger.error("Read from json error, json: {}", json, e);
        }
        return null;
    }


    /**
     * 将json字符串转为JsonNode实例
     *
     * @param json json字符串
     * @return JsonNode实例
     */
    public static JsonNode parse(String json) {
        try {
            return MAPPER.readTree(json);
        } catch (IOException e) {
            return null;
        }
    }


    /**
     * 从JsonNode中获取Double类型的值
     *
     * @param node JsonNode实例
     * @param key  值对应的Key
     * @return Double类型的值
     */
    public static Double getDouble(JsonNode node, String key) {
        return getDouble(node, key, 0.0);
    }


    /**
     * 从JsonNode中获取Double类型的值
     *
     * @param node         JsonNode实例
     * @param key          值对应的Key
     * @param defaultValue 默认值
     * @return Double类型的值
     */
    public static Double getDouble(JsonNode node, String key, Double defaultValue) {
        if (node.has(key)) {
            JsonNode target = node.get(key);
            if (target.isIntegralNumber() || target.isDouble()) {
                return target.asDouble();
            }
        }
        return defaultValue;
    }


    /**
     * 从JsonNode中获取Long类型的值
     *
     * @param node JsonNode实例
     * @param key  值对应的Key
     * @return Long类型的值
     */
    public static Long getLong(JsonNode node, String key) {
        return getLong(node, key, 0L);
    }


    /**
     * 从JsonNode中获取Long类型的值
     *
     * @param node         JsonNode实例
     * @param key          值对应的Key
     * @param defaultValue 默认值
     * @return Long类型的值
     */
    public static Long getLong(JsonNode node, String key, Long defaultValue) {
        if (node.has(key)) {
            JsonNode target = node.get(key);
            if (target.canConvertToLong()) {
                return node.get(key).asLong();
            }
        }
        return defaultValue;
    }


    /**
     * 从JsonNode中获取Int类型的值
     *
     * @param node JsonNode实例
     * @param key  值对应的Key
     * @return Int类型的值
     */
    public static Integer getInt(JsonNode node, String key) {
        return getInt(node, key, 0);
    }


    /**
     * 从JsonNode中获取Int类型的值
     *
     * @param node         JsonNode实例
     * @param key          值对应的Key
     * @param defaultValue 默认值
     * @return Int类型的值
     */
    public static Integer getInt(JsonNode node, String key, Integer defaultValue) {
        if (node.has(key)) {
            JsonNode target = node.get(key);
            if (target.canConvertToInt()) {
                return node.get(key).asInt();
            }
        }
        return defaultValue;
    }

    /**
     * 从JsonNode中获取Bool类型的值
     *
     * @param node JsonNode实例
     * @param key  值对应的Key
     * @return Bool类型的值
     */
    public static Boolean getBool(JsonNode node, String key) {
        return getBool(node, key, false);
    }


    /**
     * 从JsonNode中获取Bool类型的值
     *
     * @param node         JsonNode实例
     * @param key          值对应的Key
     * @param defaultValue 默认值
     * @return Bool类型的值
     */
    public static Boolean getBool(JsonNode node, String key, Boolean defaultValue) {
        if (node.has(key) && node.get(key).isBoolean()) {
            return node.get(key).asBoolean();
        }
        return defaultValue;
    }


    /**
     * 从JsonNode中获取文本类型的值
     *
     * @param node JsonNode实例
     * @param key  值对应的Key
     * @return 文本类型的值
     */
    public static String getText(JsonNode node, String key) {
        return getText(node, key, "");
    }


    /**
     * 从JsonNode中获取文本类型的值
     *
     * @param node         JsonNode实例
     * @param key          值对应的Key
     * @param defaultValue 默认值
     * @return 文本类型的值
     */
    public static String getText(JsonNode node, String key, String defaultValue) {
        if (node.has(key) && node.get(key).isTextual()) {
            return node.get(key).asText();
        }
        return defaultValue;
    }


    /**
     * 创建ObjectNode实例
     *
     * @return ObjectNode实例
     */
    public static ObjectNode createObjectNode() {
        return MAPPER.createObjectNode();
    }


    /**
     * 创建ArrayNode实例
     *
     * @return ArrayNode实例
     */
    public static ArrayNode createArrayNode() {
        return MAPPER.createArrayNode();
    }


    private JsonKit() {
        throw new UnsupportedOperationException("Private constructor, cannot be accessed.");
    }

}

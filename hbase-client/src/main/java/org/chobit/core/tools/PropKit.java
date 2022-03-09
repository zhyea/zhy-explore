package org.chobit.core.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * @author zhangrui137
 */
public final class PropKit {

    private static final Logger logger = LoggerFactory.getLogger(PropKit.class);

    private static final Map<Object, Object> m = new Properties();

    /**
     * 加载配置文件
     *
     * @param propertyFilePath 配置文件名称，通常使用“/name.properties”这样的值
     * @return 配置信息
     */
    public static Properties load(String propertyFilePath) {
        Properties p = new Properties();
        try (InputStream in = PropKit.class.getResourceAsStream(propertyFilePath)) {
            if (null != in) {
                p.load(in);
                m.putAll(p);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot read properties from:" + propertyFilePath, e);
        }
        return p;
    }

    public static String getProp(String key, String defaultValue) {
        Object oVal = m.get(key);
        String sVal = (oVal instanceof String) ? (String) oVal : null;
        return (null == sVal) ? defaultValue : sVal;
    }

    public static String getProp(String key) {
        return getProp(key, null);
    }

    public static Object setProp(String key, String value) {
        return m.put(key, value);
    }

    public static Integer getInt(String key, Integer defaultValue) {
        String value = getProp(key);
        return (value != null) ? Integer.parseInt(value) : defaultValue;
    }

    public static Integer getInt(String key) {
        return getInt(key, 0);
    }

    public static Double getDouble(String key, Double defaultValue) {
        String value = getProp(key);
        return (value != null) ? Double.parseDouble(value) : defaultValue;
    }

    public static Double getDouble(String key) {
        return getDouble(key, 0.0);
    }

    public static Boolean getBoolean(String key, Boolean defaultValue) {
        String value = getProp(key);
        return (value != null) ? Boolean.parseBoolean(value) : defaultValue;
    }

    public static Boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    private PropKit() {
        throw new UnsupportedOperationException("Private constructor, cannot be accessed.");
    }

}

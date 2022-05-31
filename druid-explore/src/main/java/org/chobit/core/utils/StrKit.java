package org.chobit.core.utils;

import java.text.DecimalFormat;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author robin
 */
public final class StrKit {

    /**
     * 判断字符串为空
     *
     * @param str 目标字符串
     * @return 字符串是否为空
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((!Character.isWhitespace(str.charAt(i)))) {
                return false;
            }
        }
        return true;
    }


    /**
     * 判断字符串不为空
     *
     * @param str 目标字符串
     * @return 字符串是否不为空
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }


    /**
     * 生成uuid
     *
     * @return uuid
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


    /**
     * 将数值字符串转为整型数值
     *
     * @param s 数值字符串
     * @return 整型数值
     */
    public static int toInt(final String s) {
        try {
            return Integer.parseInt(s.trim());
        } catch (Exception e) {
            return 0;
        }
    }


    /**
     * 将数值字符串转为长整型数值
     *
     * @param s 数值字符串
     * @return 长整型数值
     */
    public static long toLong(String s) {
        try {
            return Long.parseLong(s.trim());
        } catch (Exception e) {
            return 0L;
        }
    }


    /**
     * 将数值字符串转为浮点型数值
     *
     * @param s 数值字符串
     * @return 浮点型数值
     */
    public static double toDouble(String s) {
        try {
            return Double.parseDouble(s.trim());
        } catch (Exception e) {
            return 0.0;
        }
    }


    /**
     * 将数值字符串转为布尔型数值
     *
     * @param s 布尔值字符串
     * @return 布尔型数值
     */
    public static boolean toBool(String s) {
        try {
            return Boolean.parseBoolean(s.trim());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 整型判断正则表达式
     */
    private static final Pattern PATTERN_INTEGER = Pattern.compile("^[-+]?[\\d]*$");

    /**
     * 判断字符串是否是整数字符串
     *
     * @param str 字符串
     * @return 是否是整数字符串
     */
    public static boolean isInteger(String str) {
        if (isBlank(str)) {
            return false;
        }
        return PATTERN_INTEGER.matcher(str.trim()).matches();
    }

    /**
     * 浮点型判断正则表达式
     */
    private static final Pattern PATTERN_DOUBLE = Pattern.compile("^[-+]?\\d+(\\.\\d+)?$");

    /**
     * 判断字符串是否是浮点数字符串
     *
     * @param str 字符串
     * @return 是否是整数字符串
     */
    public static boolean isDouble(String str) {
        if (isBlank(str)) {
            return false;
        }
        return PATTERN_DOUBLE.matcher(str.trim()).matches();
    }


    /**
     * 判断字符串是否是布尔型字符串
     *
     * @param str 字符串
     * @return 是否是布尔型字符串
     */
    public static boolean isBool(String str) {
        if (isBlank(str)) {
            return false;
        }
        String s = str.toLowerCase().trim();
        return "true".equals(s) || "false".equals(s);
    }


    /**
     * 将对象转字符串
     *
     * @param src 原始对象
     * @param <T> 原始对象类型
     * @return 原始对象对应的字符串
     */
    public static <T> String toStr(T src) {
        if (null == src) {
            return "";
        }
        return String.valueOf(src);
    }


    /**
     * 格式化数字
     *
     * @param pattern 格式
     * @param value   数值
     * @return 格式化后的数字
     */
    public static String format(String pattern, double value) {
        return new DecimalFormat(pattern).format(value);
    }


    /**
     * 大写字母表达式
     */
    private static final Pattern PATTERN_UPPER = Pattern.compile("[A-Z]");

    /**
     * 驼峰格式转下划线
     *
     * @param str 原始字符串
     * @return 驼峰转下划线后的字符串
     */
    public static String humpToLine(String str) {
        Matcher matcher = PATTERN_UPPER.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }


    /**
     * 驼峰格式转下划线
     *
     * @param str 原始字符串
     * @return 驼峰转下划线后的字符串
     */
    public static String humpToLine2(String str) {
        return str.replaceAll("[A-Z]", "_$0").toLowerCase();
    }


    /**
     * 下划线+字符 表达式
     */
    private static final Pattern PATTERN_LINE = Pattern.compile("_(\\w)");

    /**
     * 下划线转驼峰
     *
     * @param src 原始字符串
     * @return 下划线转驼峰后的字符串
     */
    public static String lineToHump(String src) {
        Objects.requireNonNull(src);

        src = src.toLowerCase();
        Matcher matcher = PATTERN_LINE.matcher(src);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }


    /**
     * 根据正则表达式从字符串中提取数据
     *
     * @param source 源字符串
     * @param regex  正则表达式
     * @return 符合正则表达式的字符串
     */
    public static String takeSubWithRegex(String source, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return takeSubWithPattern(source, pattern);
    }


    /**
     * 根据正则表达式从字符串中提取数据
     *
     * @param source  源字符串
     * @param pattern Pattern实例
     * @return 符合正则表达式的字符串
     */
    public static String takeSubWithPattern(String source, Pattern pattern) {
        Matcher matcher = pattern.matcher(source);
        return takeFromMatcher(matcher);
    }


    /**
     * 基于Matcher提取数据
     *
     * @param matcher Matcher实例
     * @return Matcher实例匹配到的数据
     */
    public static String takeFromMatcher(Matcher matcher) {
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }


    /**
     * 执行异或运算
     *
     * @param str 原始字符串
     * @param key key
     * @return 异或运算结果
     */
    public static byte[] xor(String str, String key) {
        if (isBlank(str) || isBlank(key)) {
            return new byte[]{};
        }

        char[] infoChar = str.toCharArray();
        char[] keyChar = key.toCharArray();
        byte[] result = new byte[infoChar.length];
        for (int i = 0; i < infoChar.length; i++) {
            result[i] = (byte) ((infoChar[i] ^ keyChar[i % keyChar.length]) & 0xFF);
        }

        return result;
    }


    /**
     * 从多个字符串中依序取第一个不为空的值
     *
     * @param args 字符串参数
     * @return 第一个不为空的值
     */
    public static String coalesce(String... args) {
        for (String s : args) {
            if (isNotBlank(s)) {
                return s;
            }
        }
        return null;
    }


    /**
     * 拼接字符串
     *
     * @param separator 分隔符
     * @param elements  要拼接的元素
     * @return 字符串
     */
    public static String join(String separator, Object... elements) {
        StringBuilder builder = new StringBuilder();
        for (Object ele : elements) {
            if (builder.length() > 0) {
                builder.append(separator);
            }
            builder.append(ele);
        }
        return builder.toString();
    }


    private StrKit() {
        throw new UnsupportedOperationException("Private constructor, cannot be accessed.");
    }
}

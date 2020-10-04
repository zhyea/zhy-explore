package org.chobit.bytebuddy.tools;

import java.util.UUID;


/**
 * 字符串操作工具类
 */
public abstract class Strings {


    /**
     * 生成UUID字符串
     */
    public static String uuid() {
        return UUID.randomUUID().toString();
    }


    /**
     * 判断字符串是否不为空
     */
    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }


    /**
     * 判断字符串是否为空
     */
    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    private Strings() {
        throw new UnsupportedOperationException("Private constructor, cannot be accessed.");
    }

}

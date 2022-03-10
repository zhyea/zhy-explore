package org.chobit.core.tools;

import java.util.Collection;
import java.util.UUID;

/**
 * @author zhangrui137
 */
public final class ToolKit {


    public static <T> boolean isEmpty(Collection<T> coll) {
        return null == coll || coll.isEmpty();
    }


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


    public static String uuid() {
        return UUID.randomUUID().toString();
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }


    private ToolKit() {
    }

}

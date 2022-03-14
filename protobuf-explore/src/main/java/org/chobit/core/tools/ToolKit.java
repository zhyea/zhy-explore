package org.chobit.core.tools;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class ToolKit {

    /**
     * 生成uuid
     *
     * @return uuid
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


    /**
     * 根据提供的元素创建列表
     *
     * @param elements 列表元素
     * @param <T>      列表元素类型
     * @return 新建的列表
     */
    public static <T> List<T> listOf(T... elements) {
        List<T> list = new LinkedList<>();
        if (null == elements) {
            return list;
        }
        Collections.addAll(list, elements);
        return list;
    }


    private ToolKit() {
    }

}

package org.chobit.spring.ext;

import static org.chobit.spring.ext.DsType.WRITE;

/**
 * @author robin
 */
public final class DsContextHolder {


    private static ThreadLocal<DsType> context = new ThreadLocal<>();


    public static void set(DsType type) {
        if (null != type) {
            context.set(type);
        }
    }


    public static DsType getDbType() {
        DsType type = context.get();
        return (null == type ? WRITE : type);
    }


    public static void clear() {
        context.remove();
    }


    private DsContextHolder() {
        throw new UnsupportedOperationException("Private constructor, cannot be accessed!");
    }
}

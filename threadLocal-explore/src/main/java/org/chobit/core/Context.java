package org.chobit.core;

import static org.chobit.core.tools.Strings.isBlank;
import static org.chobit.core.tools.Strings.uuid;

public abstract class Context {


    //private static final ThreadLocal<String> traceIds = new ThreadLocal<>();

    private static final InheritableThreadLocal<String> traceIds = new InheritableThreadLocal<>();


    public static String getTraceId() {
        String id = traceIds.get();
        if (isBlank(id)) {
            traceIds.set(uuid());
            id = traceIds.get();
        }

        return id;
    }


    private Context() {
        throw new UnsupportedOperationException("Private constructor, cannot be accessed.");
    }

}

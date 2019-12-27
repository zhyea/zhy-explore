package org.chobit.bytebuddy.trace;


import static org.chobit.bytebuddy.tools.Strings.isBlank;
import static org.chobit.bytebuddy.tools.Strings.uuid;

/**
 * 用来保存traceId
 */
public class TrackContext {


    private static final ThreadLocal<String> trackLocal = new ThreadLocal<String>();


    public static void clear() {
        trackLocal.remove();
    }


    public static String getTraceId() {
        String id = trackLocal.get();
        if (isBlank(id)) {
            id = uuid();
            setTraceId(id);
        }
        return id;
    }


    public static void setTraceId(String traceId) {
        trackLocal.set(traceId);
    }

}

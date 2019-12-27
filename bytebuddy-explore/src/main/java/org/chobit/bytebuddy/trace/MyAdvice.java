package org.chobit.bytebuddy.trace;

import net.bytebuddy.asm.Advice;

import java.util.Arrays;

public class MyAdvice {


    @Advice.OnMethodEnter()
    public static String enter(@Advice.Origin("#t") String className,
                               @Advice.Origin("#m") String methodName) {
        return TrackContext.getTraceId();
    }


    @Advice.OnMethodExit()
    public static void exit(@Advice.Origin("#t") String className,
                            @Advice.Origin("#m") String methodName,
                            @Advice.AllArguments Object[] args,
                            @Advice.Enter String traceId) {
        System.out.println("link trace: " + traceId + " " + className + "." + methodName + "(" + Arrays.toString(args) + ")");
    }
}

package org.chobit.bytebuddy.timing;

import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class TimingInterceptor {

    @RuntimeType
    public static Object intercept(@Origin Method method,
                                   @SuperCall Callable<?> callable) throws Exception {

        Timing timing = method.getAnnotation(Timing.class);
        String name = timing.value();
        if ("".equals(name)) {
            name = method.toString();
        }

        long start = System.currentTimeMillis();
        try {
            return callable.call();
        } catch (Throwable t) {
            TimingClerk.updateFailed(name, System.currentTimeMillis() - start);
            throw t;
        } finally {
            TimingClerk.update(name, System.currentTimeMillis() - start);
        }
    }

}

package org.chobit.bytebuddy;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.MethodDelegation;

import java.lang.instrument.Instrumentation;

import static net.bytebuddy.matcher.ElementMatchers.*;

public class MyAgent {

    private static void premain(String options, Instrumentation ins) {
        new AgentBuilder.Default()
                .type(declaresMethod(isAnnotatedWith(Timing.class)))
                .transform(
                        (builder, type, classLoader, module) ->
                                builder.method(isAnnotatedWith(Timing.class))
                                        .intercept(MethodDelegation.to(TimingInterceptor.class)
                                        )
                ).installOn(ins);
    }

}

package org.chobit.bytebuddy;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.MethodDelegation;
import org.chobit.bytebuddy.timing.Timing;
import org.chobit.bytebuddy.timing.TimingInterceptor;

import java.lang.instrument.Instrumentation;

import static net.bytebuddy.matcher.ElementMatchers.declaresMethod;
import static net.bytebuddy.matcher.ElementMatchers.isAnnotatedWith;

public class TimingAgent {

    private static void premain(String options, Instrumentation ins) {
        new AgentBuilder.Default()
                .type(declaresMethod(isAnnotatedWith(Timing.class)))
                .transform(
                        (builder, type, classLoader, module) ->
                                builder.method(isAnnotatedWith(Timing.class))
                                        .intercept(MethodDelegation.to(TimingInterceptor.class))
                ).installOn(ins);
    }





}

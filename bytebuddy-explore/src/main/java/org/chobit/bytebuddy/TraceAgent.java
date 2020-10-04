package org.chobit.bytebuddy;

import com.alibaba.ttl.threadpool.agent.TtlAgent;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;
import org.chobit.bytebuddy.trace.MyAdvice;

import java.lang.instrument.Instrumentation;

public class TraceAgent {


    public static void premain(String args, Instrumentation ins) {

        TtlAgent.premain(args, ins);

        AgentBuilder agentBuilder = new AgentBuilder.Default();

        AgentBuilder.Transformer transformer = (builder, typeDescription, classLoader, javaModule) ->
                builder.visit(
                        Advice.to(MyAdvice.class)
                                .on(ElementMatchers.isMethod()
                                        .and(ElementMatchers.any())
                                        .and(ElementMatchers.not(ElementMatchers.nameStartsWith("main")))));


        agentBuilder = agentBuilder.type(ElementMatchers.nameContains("MyApp")).transform(transformer);


        AgentBuilder.Listener listener = new AgentBuilder.Listener.Adapter() {
            @Override
            public void onTransformation(TypeDescription typeDescription,
                                         ClassLoader classLoader,
                                         JavaModule javaModule,
                                         boolean b,
                                         DynamicType dynamicType) {
                System.out.println("transform: " + typeDescription);
            }
        };

        agentBuilder.with(listener).installOn(ins);
    }

}

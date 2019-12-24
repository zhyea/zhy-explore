package org.chobit.javassist;

import java.lang.instrument.Instrumentation;

public class MyAgent {

    private static void premain(String options, Instrumentation ins) {
        ins.addTransformer(new MyTransformer());
    }

}

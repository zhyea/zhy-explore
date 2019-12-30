package org.chobit.core;

import static org.chobit.core.tools.Strings.uuid;

public class ThreadLocalApp {

    private static final ThreadLocal<String> traceIds = new ThreadLocal<>();

    public static void main(String[] args) {
        traceIds.set(uuid());

        hello();
        hello2();
        new Thread(ThreadLocalApp::child).start();
    }

    private static void hello() {
        System.out.println(traceIds.get() + " - " + "Hello World!");
    }

    private static void hello2() {
        System.out.println(traceIds.get() + " - " + "Hello Java!");
    }


    private static void child() {
        System.out.println(traceIds.get() + " - " + "child");
    }


}

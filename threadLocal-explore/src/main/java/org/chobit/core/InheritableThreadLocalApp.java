package org.chobit.core;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.chobit.core.tools.Strings.uuid;

public class InheritableThreadLocalApp {

    private static final InheritableThreadLocal<String> traceIds
            = new InheritableThreadLocal<>();


    public static void main(String[] args) throws InterruptedException {
        traceIds.set(uuid());

        hello2();
        new Thread(InheritableThreadLocalApp::child).start();

        child1();

        TimeUnit.MILLISECONDS.sleep(6);

        child2();

        TimeUnit.MILLISECONDS.sleep(6);
        executorService.shutdown();
    }

    private static void hello2() {
        System.out.println(traceIds.get() + " - " + "Hello Java!");
    }


    private static void child() {
        System.out.println(traceIds.get() + " - " + "child");
        new Thread(InheritableThreadLocalApp::grandchild).start();
    }


    private static void grandchild() {
        System.out.println(traceIds.get() + " - " + "child");
    }

    private static ExecutorService executorService = Executors.newFixedThreadPool(3);

    private static void child1() {
        new Thread(() -> {
            traceIds.set(uuid());
            for (int i = 0; i < 3; i++) {
                executorService.submit(() -> System.out.println(traceIds.get() + " - " + "child-1"));
            }
        }).start();
    }

    private static void child2() {
        new Thread(() -> {
            traceIds.set(uuid());
            for (int i = 0; i < 3; i++) {
                executorService.submit(() -> System.out.println(traceIds.get() + " - " + "child-2"));
            }
        }).start();
    }
}

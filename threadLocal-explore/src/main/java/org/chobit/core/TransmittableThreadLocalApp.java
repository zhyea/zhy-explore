package org.chobit.core;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.chobit.core.tools.Strings.uuid;

public class TransmittableThreadLocalApp {


    private static final TransmittableThreadLocal<String> traceIds
            = new TransmittableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {

        child1();

        TimeUnit.MILLISECONDS.sleep(30L);

        child2();

        TimeUnit.MILLISECONDS.sleep(1000L);

        executorService.shutdown();
    }


    private static ExecutorService executorService = Executors.newFixedThreadPool(3);


    private static void child1() {
        new Thread(() -> {
            traceIds.set(uuid());
            for (int i = 0; i < 3; i++) {
                executorService.submit(TtlRunnable.get(() -> System.out.println(traceIds.get() + " - " + "child-1")));
            }
        }).start();
    }

    private static void child2() {
        new Thread(() -> {
            traceIds.set(uuid());
            for (int i = 0; i < 3; i++) {
                executorService.submit(TtlRunnable.get(() -> System.out.println(traceIds.get() + " - " + "child-2")));
            }
        }).start();
    }

}

package org.chobit.core;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author robin
 */
public class MyApp {

    public static void main(String[] args) throws InterruptedException {
        joinTest();
    }


    private static void joinTest() throws InterruptedException {
        AtomicInteger summer = new AtomicInteger(0);
        Runnable r = summer::incrementAndGet;

        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(r);
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(summer.get());
    }

}

package org.chobit.core;

import java.util.concurrent.*;

/**
 * @author robin
 */
public class MyApp {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Integer> c = () -> 1;

        // ThreadLocalRandom.current().nextInt()

        int sum = 0;
        for (int i = 0; i < 100; i++) {
            FutureTask<Integer> t = new FutureTask<>(c);
            new Thread(t).start();
            sum += t.get();
        }
        System.out.println(sum);
    }


}

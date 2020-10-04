package org.chobit.core;

import java.util.concurrent.*;


/**
 * 使用Future进行异步计算的尝试
 */
public class ComputeUseFuture {


    private static ExecutorService executorService = Executors.newFixedThreadPool(1);


    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Future<Integer> result = sumAsync(100);

        while (!result.isDone()) {
            System.out.println("--------------->> 子线程未计算完成，需等待");
            TimeUnit.MILLISECONDS.sleep(100);
        }

        System.out.println("计算已完成");
        System.out.println(result.get());

        executorService.shutdown();
    }


    private static Future<Integer> sumAsync(int num) {
        Task task = new Task(num);
        return executorService.submit(task);
    }

}

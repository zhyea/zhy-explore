package org.chobit.core;

import java.util.concurrent.*;


/**
 * 使用FutureTask执行异步运算
 */
public class ComputeUseFutureTask {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        FutureTask<Integer> task = sumAsync(100);

        while (!task.isDone()) {
            System.out.println("--------------->> 子线程未计算完成，需等待");
            TimeUnit.MILLISECONDS.sleep(100);
        }

        System.out.println("计算已完成");
        System.out.println(task.get());
    }


    private static FutureTask<Integer> sumAsync(int num) {
        ExecutorService service = Executors.newSingleThreadExecutor();

        Task task = new Task(num);
        FutureTask<Integer> ft = new FutureTask<>(task);

        service.submit(ft);

        return ft;
    }

}

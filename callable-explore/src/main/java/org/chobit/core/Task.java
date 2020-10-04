package org.chobit.core;

import java.util.concurrent.Callable;

public class Task implements Callable<Integer> {

    private int num;

    public Task(int num) {
        this.num = num;
    }

    @Override
    public Integer call() throws Exception {

        System.out.println("-------->>> 子线程开始执行计算");
        Thread.sleep(3000);

        int sum = 0;
        for (int i = 0; i < this.num; i++) {
            sum += i;
        }

        return sum;
    }


}

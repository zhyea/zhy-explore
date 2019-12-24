package org.chobit.bytebuddy;

import java.util.concurrent.TimeUnit;

public class MyTest {


    public static void main(String[] args) throws InterruptedException {
        System.out.println(hello());
        System.out.println(true);
        TimeUnit.SECONDS.sleep(1);
    }


    @Timing
    private static String hello() {
        return "Hello";
    }

}

package org.chobit.bytebuddy;

import org.chobit.bytebuddy.timing.Timing;

public class MyApp {

    @Timing
    public static void main(String[] args) {
        try {
            for (int i = 0; i < 6; i++) {
                System.out.println(hello("chobit"));
            }
            error();
        } catch (Exception e) {
            e.printStackTrace();
        }
        m1();

        new Thread(() -> m1()).start();
        new Thread(() -> m1()).start();
    }

    @Timing("the method name is hello()")
    private static String hello(String name) {
        return "Hello " + name + "!";
    }


    @Timing
    private static void error() {
        System.out.println("error is coming");
        throw new RuntimeException("I just do this on purpose");
    }


    private static void m1() {
        System.out.println("This is m1, i will call m2.");
        m2();
    }

    private static void m2() {
        System.out.println("This is m2, i will call m3.");
        m3();
    }

    private static void m3() {
        System.out.println("This is m3, i will call no method.");
    }

}

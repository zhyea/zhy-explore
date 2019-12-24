package org.chobit.bytebuddy;

public class MyApp {

    @Timing
    public static void main(String[] args) {
        try {
            System.out.println(hello());
            error();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Timing("the method name is hello()")
    private static String hello() {
        return "Hello";
    }


    @Timing
    private static void error() {
        System.out.println("error is coming");
        throw new RuntimeException("I just do this on purpose");
    }

}

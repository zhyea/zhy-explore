package org.chobit.bytebuddy;

public class MyApp {

    public MyApp() {
        try {
            System.out.println(1);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @Timing
    public static void main(String[] args) {
        System.out.println(hello());
    }

    @Timing
    private static String hello() {
        return "Hello";
    }


}

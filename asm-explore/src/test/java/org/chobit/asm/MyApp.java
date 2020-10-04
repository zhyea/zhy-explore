package org.chobit.asm;

public class MyApp {

    public MyApp() {
        try {
            System.out.println(1);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static void main(String[] args) {
        boolean success = true;
        long start = System.currentTimeMillis();
        try {
            System.out.println(hello());
        } catch (Throwable t) {
            success = false;
            throw t;
        } finally {
            TimeClerk.update(success, "zzzzz", System.currentTimeMillis() - start);
        }
    }


    private static String hello() {
        try {
            return "Hello";
        } catch (Throwable t) {
            t.printStackTrace();
            throw t;
        }
    }


}

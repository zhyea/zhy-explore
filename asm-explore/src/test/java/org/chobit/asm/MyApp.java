package org.chobit.asm;

public class MyApp {

    public static void main(String[] args) {
        boolean success = true;
        long start = System.currentTimeMillis();
        try {
            System.out.println("This is a test!");
        } catch (Throwable t) {
            success = false;
            throw t;
        } finally {
            TimeClerk.update(success, "zzzzz", System.currentTimeMillis() - start);
        }
    }

}

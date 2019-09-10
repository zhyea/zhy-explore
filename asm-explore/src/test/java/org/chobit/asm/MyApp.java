package org.chobit.asm;

public class MyApp {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        try {
            System.out.println("This is a test!");
        } catch (Throwable t) {
            t.printStackTrace();
            TimeClerk.updateFailed("zzzzz", System.currentTimeMillis() - start);
            throw t;
        } finally {
            TimeClerk.update("zzzzz", System.currentTimeMillis() - start);
        }
    }

}

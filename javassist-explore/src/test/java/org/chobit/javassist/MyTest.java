package org.chobit.javassist;

public class MyTest {

    public static void main(String[] args) {
        boolean n = false;
        for (int i = 0; i < 6; i++) {
            System.out.println(hello());
        }
        n = true;
        System.out.println(n);
    }

    private static String hello() {
        return "Hello";
    }
}

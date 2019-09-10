package org.chobit.asm;

public class MyTest {

    public static void main(String[] args) {
        for (int i = 0; i < 6; i++) {
            System.out.println(hello());
        }
    }


    private static String hello() {
        return "Hello";
    }

}

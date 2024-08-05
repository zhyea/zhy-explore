package org.chobit.apt;

import org.chobit.core.Person;

public class MyTest {

    public static void main(String[] args) {
        Person p = new Person();
        p.setName("tom");
        p.setAge(13);

        System.out.println(p);
    }

}

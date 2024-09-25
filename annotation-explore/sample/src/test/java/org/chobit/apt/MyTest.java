package org.chobit.apt;

import org.chobit.core.Person;
import org.chobit.core.Student;

public class MyTest {

    public static void main(String[] args) {
        Person p = new Person();
        p.setName("tom");
        p.setAge(13);

        System.out.println(p);


        Student s = new Student();
        s.setName("tom");
        s.setAge(13);
        s.setNo("20070771");

        System.out.println(s);

    }

}

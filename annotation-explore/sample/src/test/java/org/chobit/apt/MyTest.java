package org.chobit.apt;

import org.chobit.core.Person;
import org.chobit.core.School;
import org.chobit.core.Student;

import java.util.LinkedList;
import java.util.List;

public class MyTest {

    public static void main(String[] args) {
        Person p = new Person();
        p.setName("tom");
        p.setAge(13);

        System.out.println(p);

        School school = buildSchoolInstance();

        System.out.println(school);

    }


    private static School buildSchoolInstance() {
        School school = new School();
        school.setName("文化创意小学");
        school.setLocation("朝阳区北苑路文化创意大厦");

        List<Student> students = new LinkedList<>();

        Student s1 = new Student();
        s1.setName("tom");
        s1.setAge(13);
        s1.setNo("20070771");


        Student s2 = new Student();
        s2.setName("Jerry");
        s2.setAge(8);
        s2.setNo("20220221");


        Student s3 = new Student();
        s3.setName("Harry");
        s3.setAge(16);
        s3.setNo("20240012");


        Student s4 = new Student();
        s4.setName("Polly");
        s4.setAge(3);
        s4.setNo("20221214");


        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);

        school.setStudents(students);

        return school;
    }

}

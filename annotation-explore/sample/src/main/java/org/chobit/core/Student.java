package org.chobit.core;


public class Student {



    private String name;


    private String no;


    private int age;


    @Override
    public String toString() {
        return org.chobit.apt.JsonStringSerializer.toJson(this);
    }
}

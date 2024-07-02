package org.chobit.spring.tools;


import org.chobit.spring.ext.mybatis.type.DlpTypeHandler;

public class MyApp {

    public static void main(String[] args) {
        System.out.println(DlpTypeHandler.class.getClass());
        System.out.println(DlpTypeHandler.class.getCanonicalName());
        System.out.println(DlpTypeHandler.class.getName());
        System.out.println(DlpTypeHandler.class.getPackage());
        System.out.println(DlpTypeHandler.class.getTypeName());
    }

}

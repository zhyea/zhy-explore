package org.chobit.spring.app1.web;

import org.chobit.spring.app1.ApiTestBase;
import org.junit.Test;

public class HelloController1Test extends ApiTestBase {

    @Override
    protected String parentPath() {
        return "/api";
    }


    @Test
    public void hello() {
        Object obj = testGet("/hello/chobit");
        System.out.println(obj);
    }

}

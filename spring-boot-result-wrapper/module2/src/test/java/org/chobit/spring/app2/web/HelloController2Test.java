package org.chobit.spring.app2.web;

import org.chobit.spring.app2.ApiTestBase;
import org.junit.Test;

public class HelloController2Test extends ApiTestBase {

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

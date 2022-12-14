package org.chobit.spring.web;

import org.chobit.spring.ApiTestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyApiTest extends ApiTestBase {

    @Override
    protected String parentPath() {
        return "/api/zhyea";
    }


    @Test
    public void get() {
        int i = testGet("/1", Integer.class);
        Assertions.assertEquals(1, i);
    }


}

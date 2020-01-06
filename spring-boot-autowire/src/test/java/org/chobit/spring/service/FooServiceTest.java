package org.chobit.spring.service;

import org.chobit.spring.TestBase;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class FooServiceTest extends TestBase {

    @Autowired
    private FooService1 service1;
    @Autowired
    private FooService2 service2;
    @Autowired
    private FooService3 service3;
    @Autowired
    private FooService4 service4;
    @Autowired
    private FooService5 service5;
    @Autowired
    private FooService6 service6;


    @Test
    public void format() {
        Assert.assertEquals("foo", service1.formatFoo());
        Assert.assertEquals("foo", service2.formatFoo());
        Assert.assertEquals("foo", service3.formatFoo());
        Assert.assertEquals("foo", service4.formatFoo());
        Assert.assertEquals("foo", service5.formatFoo());
        Assert.assertEquals("foo", service6.formatFoo());
    }


}

package org.chobit.spring.service;


import org.chobit.spring.TestBase;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RequireServiceTest extends TestBase {

    @Autowired
    private RequireService requireService;


    @Test
    public void getFooDao() {
        Assert.assertNull(requireService.getFooDao());
    }

    
}

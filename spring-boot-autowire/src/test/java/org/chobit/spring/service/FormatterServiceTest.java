package org.chobit.spring.service;


import org.chobit.spring.TestBase;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class FormatterServiceTest extends TestBase {


    @Autowired
    private FormatterService formatterService;

    @Test
    public void format() {
        List<String> list = formatterService.format();
        System.out.println(list);
        Assert.assertEquals(2, list.size());
    }

}

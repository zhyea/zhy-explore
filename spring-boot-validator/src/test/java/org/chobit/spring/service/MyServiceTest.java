package org.chobit.spring.service;

import org.chobit.spring.TestBase;
import org.chobit.spring.service.MyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MyServiceTest extends TestBase {

    @Autowired
    private MyService myService;

    @Test
    public void getId() {
        String id = myService.getId();
        System.out.println(id);
    }

}

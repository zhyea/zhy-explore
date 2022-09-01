package org.chobit.spring;

import org.chobit.spring.service.MyService;
import org.junit.Test;
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

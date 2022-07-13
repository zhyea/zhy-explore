package org.chobit.spring.service;

import org.chobit.spring.TestBase;
import org.chobit.spring.handler.Handler;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class AppContextTest extends TestBase {


    @Autowired
    private ApplicationContext context;

    @Test
    public void getBean() {
        Handler ha = context.getBean("handler-a", Handler.class);
        ha.handle();

        Handler hb = context.getBean("handler-b", Handler.class);
        hb.handle();
    }

}

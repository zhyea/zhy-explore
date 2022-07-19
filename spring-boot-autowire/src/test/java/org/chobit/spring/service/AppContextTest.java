package org.chobit.spring.service;

import org.chobit.spring.TestBase;
import org.chobit.spring.handler.Handler;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.Map;

public class AppContextTest extends TestBase {


    @Autowired
    private ApplicationContext context;
    @Autowired
    private Map<String, Handler> handlerMap;

    @Test
    public void getBean() {
        Handler ha = context.getBean("handler-a", Handler.class);
        ha.handle();

        Handler hb = context.getBean("handler-b", Handler.class);
        hb.handle();
    }


    @Test
    public void printMap() {
        handlerMap.entrySet().forEach(System.out::println);
    }

}

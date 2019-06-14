package org.chobit.spring.tools;

import org.chobit.spring.bean.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class WorkerBeanFactory {


    @Autowired
    private ApplicationContext context;


    public Worker get(String name) {
        return context.getBean(name, Worker.class);
    }

}

package org.chobit.spring.runner;

import org.chobit.spring.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner1 implements ApplicationRunner {

    @Autowired
    private MyService myService;

    @Override
    public void run(ApplicationArguments args) {
        System.out.println("--------->>> my runner1 has started");
        System.out.println(myService.getId());
    }


}

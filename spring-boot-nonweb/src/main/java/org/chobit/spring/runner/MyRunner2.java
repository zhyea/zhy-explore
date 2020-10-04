package org.chobit.spring.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner2 implements ApplicationRunner {


    @Override
    public void run(ApplicationArguments args) {
        System.out.println("--------->>> my runner2 has started");
    }


}

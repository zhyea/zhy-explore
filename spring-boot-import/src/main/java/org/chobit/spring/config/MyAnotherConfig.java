package org.chobit.spring.config;

import org.chobit.spring.bean.Worker;
import org.springframework.context.annotation.Bean;

public class MyAnotherConfig {

    @Bean("anotherTom")
    public Worker worker() {
        return new Worker("anotherTom", 2);
    }

}



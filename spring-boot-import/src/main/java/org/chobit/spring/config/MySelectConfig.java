package org.chobit.spring.config;

import org.chobit.spring.bean.Worker;
import org.springframework.context.annotation.Bean;

public class MySelectConfig {

    @Bean("selectTom")
    public Worker worker() {
        return new Worker("selectTom", 1);
    }
}

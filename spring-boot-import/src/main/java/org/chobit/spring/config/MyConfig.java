package org.chobit.spring.config;

import org.chobit.spring.bean.Worker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({MyAnotherConfig.class,
        MySelectConfig.class,
        WorkerBeanDefinitionRegistrar.class})
public class MyConfig {


    @Bean("tom")
    public Worker worker() {
        return new Worker("tom", 2);
    }


}

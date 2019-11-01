package org.chobit.spring;

import org.chobit.spring.bean.Worker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class MyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }

    @Profile("prod")
    @Bean
    public Worker tom() {
        return new Worker("Tom", 8);
    }

    @Profile("dev")
    @Bean
    public Worker jim() {
        return new Worker("Jim", 8);
    }


    @Profile("test")
    @Bean
    @ConfigurationProperties(prefix = "custom.worker1")
    public Worker getWorker() {
        return new Worker();
    }

}

package org.chobit.spring.service;


import org.chobit.spring.bean.Worker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;


@Service
public class WorkerService {


    @Value("custom.token")
    private String token;

    @Bean
    @ConfigurationProperties(prefix = "custom.worker")
    private Worker getWorker() {
        return new Worker();
    }

    public Worker get() {
        System.out.println("------------------------");
        return getWorker();
    }


    public String token() {
        return token;
    }

}

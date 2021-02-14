package org.chobit.spring;

import org.chobit.spring.config.EnableKafkaClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author robin
 */
@EnableKafkaClients
@SpringBootApplication
public class MyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
        System.out.println("启动成功...");
    }

}

package org.chobit.spring;

import org.chobit.spring.redlock.EnableRedLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author rui.zhang
 */
@EnableRedLock
@SpringBootApplication
public class MyApplication {


    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }


}

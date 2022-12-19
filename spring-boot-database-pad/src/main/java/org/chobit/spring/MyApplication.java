package org.chobit.spring;

import org.chobit.spring.redlock.EnableRedLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.Ordered;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author rui.zhang
 */
@EnableRedLock
@EnableTransactionManagement(order = Ordered.HIGHEST_PRECEDENCE)
@SpringBootApplication
public class MyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }

}

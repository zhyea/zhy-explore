package org.chobit.spring;

import org.chobit.spring.service.MyService;
import org.chobit.spring.tools.Threads;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

/**
 * @author robin
 */
@Component
public class MyRunner implements ApplicationRunner {


    @Autowired
    private MyService myService;

    private final ExecutorService executor = Threads.newFixedThreadPool(36);

    private static final int MAX_THREADS = 108;


    @Override
    public void run(ApplicationArguments args) {
        int count = 0;
        do {
            executor.submit(() -> myService.start());
            System.out.println(">>>>> " + count);
        } while (count++ < MAX_THREADS);
    }
}

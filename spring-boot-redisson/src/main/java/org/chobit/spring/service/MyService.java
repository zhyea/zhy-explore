package org.chobit.spring.service;

import org.chobit.spring.redlock.RedLock;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author rui.zhang
 */
@Service
public class MyService {


    @RedLock(key = "zhy")
    public void execute() {
        try {
            TimeUnit.SECONDS.sleep(3L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    @RedLock(key = "'zhy:' + #name")
    public void hello(String name) {
        System.out.println("hello " + name + "!");
    }


}

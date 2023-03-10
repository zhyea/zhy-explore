package org.chobit.spring.service;

import org.chobit.spring.redlock.RedLock;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author rui.zhang
 */
@Service
public class MyService {


    @RedLock(key = "'zhy'")
    public void execute() {
        try {
            TimeUnit.SECONDS.sleep(3L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    @RedLock(key = "#date.getTime()")
    public void hello(Date date) {
        System.out.println("hello " + date + "!");
    }


}

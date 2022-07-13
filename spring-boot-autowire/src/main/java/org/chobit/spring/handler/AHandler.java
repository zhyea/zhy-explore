package org.chobit.spring.handler;

import org.springframework.stereotype.Service;

@Service("handler-a")
public class AHandler implements Handler {


    @Override
    public void handle() {
        System.out.println("A");
    }


}

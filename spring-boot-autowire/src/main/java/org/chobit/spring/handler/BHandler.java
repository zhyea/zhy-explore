package org.chobit.spring.handler;

import org.springframework.stereotype.Service;

@Service("handler-b")
public class BHandler implements Handler {


    @Override
    public void handle() {
        System.out.println("B");
    }


}

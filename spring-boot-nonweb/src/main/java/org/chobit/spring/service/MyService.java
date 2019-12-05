package org.chobit.spring.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Service
public class MyService implements InitializingBean {


    public String getId() {
        return UUID.randomUUID().toString().replace("-", "");
    }


    @PostConstruct
    public void init() {
        System.out.println("--------->>> start from my service init method");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("--------->>> start from my service");
    }
}

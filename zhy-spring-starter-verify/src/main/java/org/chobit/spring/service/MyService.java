package org.chobit.spring.service;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * 测试Service类
 *
 * @author rui.zhang
 */
@Service
public class MyService implements InitializingBean, DisposableBean {


    public String getId() {
        return UUID.randomUUID().toString().replace("-", "");
    }


    @PostConstruct
    public void init() {
        System.out.println("--------->>> start my service init method");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("--------->>> after my service properties set");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("--------->>> destroy my service");
    }
}

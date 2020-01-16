package org.chobit.spring;

import org.springframework.beans.factory.InitializingBean;

public class HelloStarter implements InitializingBean {

    private String name;

    public HelloStarter(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("------------------->>>> Hello " + name + "!");
    }
}

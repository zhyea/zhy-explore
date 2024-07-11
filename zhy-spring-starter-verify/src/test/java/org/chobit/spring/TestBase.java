package org.chobit.spring;

import org.chobit.spring.tools.BeanKit;
import org.springframework.beans.BeansException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class TestBase implements ApplicationContextAware {


    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        BeanKit.init(context);
        System.out.println(context);
    }
}

package org.chobit.spring;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.chobit.commons.json.LocalDateTimeModule;
import org.chobit.commons.utils.JsonKit;
import org.springframework.beans.BeansException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestBase implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        ObjectMapper mapper = JsonKit.mapper();
        mapper.registerModule(new LocalDateTimeModule());
    }
}

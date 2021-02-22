package org.chobit.spring.component;

import org.chobit.spring.StringProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author robin
 */
@Component
public class ProducerService {


    @Autowired
    private StringProducerTemplate producerTemplate;


    public void send(String topic) {
        for (int i = 0; i < 100; i++) {
            producerTemplate.send(topic, String.valueOf(i));
        }
    }


}

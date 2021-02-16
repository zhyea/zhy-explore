package org.chobit.spring.component;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.chobit.spring.Processor;
import org.springframework.stereotype.Component;


/**
 * @author robin
 */
@Component("zhyyy")
public class MyProcessor implements Processor<String, String> {

    @Override
    public void process(ConsumerRecords<String, String> records) {

        for (ConsumerRecord<String, String> r : records) {
            String json = r.value();
            System.out.println(json);
        }
    }

}

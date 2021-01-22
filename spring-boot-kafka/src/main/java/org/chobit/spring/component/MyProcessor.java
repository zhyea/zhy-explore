package org.chobit.spring.component;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.chobit.kafka.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component("zhyyy")
public class MyProcessor implements Processor<String, String> {


    private static final Logger logger = LoggerFactory.getLogger(MyProcessor.class);


    @Override
    public void process(ConsumerRecords<String, String> records) {

        for (ConsumerRecord<String, String> r : records) {
            String json = r.value();
            System.out.println(json);
        }
    }

}

package org.chobit.spring.component;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.chobit.kafka.Processor;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component("zhyyy")
public class MyProcessor implements Processor<String, String> {


    @Override
    public boolean process(ConsumerRecords<String, String> records) {

        System.out.println(records.count());

        for (ConsumerRecord<String, String> r : records) {
            System.out.println(r.value());
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

}

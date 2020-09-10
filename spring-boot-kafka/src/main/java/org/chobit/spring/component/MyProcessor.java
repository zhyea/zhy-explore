package org.chobit.spring.component;

import com.ke.growth.kafka.Processor;
import com.ke.growth.kafka.ProducerAgent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Component("zhyyy")
public class MyProcessor implements Processor<String, String> {

    private static final Logger logger = LoggerFactory.getLogger("all");

    private static final long start = System.currentTimeMillis();

    private static final AtomicBoolean flag = new AtomicBoolean(true);

    private static final String[] arr = {"A1000060E3C146", "867886020602367", "867886020601690", "868468031003312"};


    @Autowired
    private ProducerAgent producerAgent;

    @Override
    public void process(ConsumerRecords<String, String> records) {

        for (ConsumerRecord<String, String> r : records) {

            String json = r.value();

            if (json.contains("BIZOPP_CLICK")) {
                System.out.println(json);
            }
        }
    }

}

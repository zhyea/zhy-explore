package org.chobit.spring.runner;

import com.ke.growth.kafka.KafkaProducer;
import org.chobit.spring.model.AdClick;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import static org.chobit.spring.tools.JsonKit.toJson;


@Component
public class MyRunner implements ApplicationRunner {


    @Autowired(required = false)
    private KafkaProducer<String, String> producer;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        int count = 0;

        String topic = "userstrategy-ad-bigc";

        if (null == producer) {
            return;
        }

        while (count < 1000000) {

            AdClick click = new AdClick(count);
            String json = toJson(click);

            producer.send(topic, json);

            count++;


            if (count % 10000 == 0) {
                System.out.println("---------------->>> " + count);
            }
        }
    }


}

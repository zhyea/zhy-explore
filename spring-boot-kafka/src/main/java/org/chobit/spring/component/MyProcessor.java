package org.chobit.spring.component;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.chobit.kafka.Processor;
import org.chobit.spring.tools.Counter;
import org.chobit.spring.tools.DateKit;
import org.chobit.spring.tools.JsonKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component("zhyyy")
public class MyProcessor implements Processor<String, String> {

    private static final Logger diffLogger = LoggerFactory.getLogger("diff");

    private static final Logger allLogger = LoggerFactory.getLogger("all");

    private static Counter hourCounter = new Counter("小时数据");
    private static Counter emptyCounter = new Counter("空数据");

    @Override
    public void process(ConsumerRecords<String, String> records) {

        for (ConsumerRecord<String, String> r : records) {
            String json = r.value();
            JsonNode root = JsonKit.parse(json);
            if (!root.has("log_time")) {
                emptyCounter.add("empty");
                continue;
            }
            String logTimeStr = root.get("log_time").asText();

            try {
                Date time = DateKit.parse(logTimeStr);
                long diff = System.currentTimeMillis() - time.getTime();
                if (diff > TimeUnit.MINUTES.toMillis(10)) {
                    diffLogger.error("TEN: delay exceed 5 minutes, src:[{}]", json);
                }
                if (diff > TimeUnit.MINUTES.toMillis(5)) {
                    diffLogger.error("FIVE: delay exceed 5 minutes, src:[{}]", json);
                }
                if (diff > TimeUnit.MINUTES.toMillis(3)) {
                    diffLogger.error("THREE: delay exceed 3 minutes, src:[{}]", json);
                }
                if (diff > TimeUnit.MINUTES.toMillis(1)) {
                    diffLogger.error("ONE: delay exceed 1 minutes, src:[{}]", json);
                }
            } catch (ParseException e) {
                e.printStackTrace();
                diffLogger.error("Parse time failed. src:[{}]", logTimeStr, e);
            }

            allLogger.info(json);
            String key = logTimeStr.substring(0, 13);
            hourCounter.add(key);
        }
    }

}

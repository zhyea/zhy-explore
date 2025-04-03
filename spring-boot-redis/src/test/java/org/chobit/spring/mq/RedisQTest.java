package org.chobit.spring.mq;

import org.chobit.spring.TestBase;
import org.chobit.spring.redisq.beetle.producer.MessageProducer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author robin
 * @since 2025/4/3 8:15
 */
public class RedisQTest extends TestBase {


	@Autowired
	private MessageProducer producer;

	private final String topic = "topic1";


	@Test
	public void produce(){
		producer.produce(topic, "hello");
	}


}

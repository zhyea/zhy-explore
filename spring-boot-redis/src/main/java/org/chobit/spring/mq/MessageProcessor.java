package org.chobit.spring.mq;

import org.chobit.spring.redisq.beetle.Message;
import org.chobit.spring.redisq.beetle.consumer.IProcessor;
import org.springframework.stereotype.Component;

/**
 * 消息处理器
 *
 * @author robin
 * @since 2025/4/3 8:10
 */
@Component("zhyyy")
public class MessageProcessor implements IProcessor {


	@Override
	public void process(Message message) {
		System.out.println(message);
	}


}

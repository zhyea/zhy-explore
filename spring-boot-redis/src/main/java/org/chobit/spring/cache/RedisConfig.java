package org.chobit.spring.cache;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.nio.charset.Charset;

/**
 * redis配置
 *
 * @author robin
 * @since 2025/4/2 22:40
 */
@Configuration
public class RedisConfig {

	@Value("${spring.redis.host}")
	private String host;
	@Value("${spring.redis.port}")
	private int port;


	@Bean("redisqConnectionFactory")
	public RedisConnectionFactory redisConnectionFactory() {
		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
		config.setHostName(host);
		config.setPort(port);
		return new LettuceConnectionFactory(config);
	}


	@Bean("redisTemplateOrder")
	public RedisTemplate<String, String> redisTemplate(
			@Qualifier("redisqConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
		return  createRedisTemplate(redisConnectionFactory);
	}



	private RedisTemplate<String, String> createRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		RedisSerializer<String> serializer = new StringRedisSerializer(Charset.defaultCharset());
		// 设置键（key）的序列化
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		// 设置value序列化
		redisTemplate.setValueSerializer(serializer);
		// 设置HashKey序列化 为啥要hashkey
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		// 设置HashValue序列化
		redisTemplate.setHashValueSerializer(serializer);
		// 默认序列化
		redisTemplate.setDefaultSerializer(new StringRedisSerializer());
		return redisTemplate;
	}


}

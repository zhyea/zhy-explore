package org.chobit.spring.redis;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

@Configuration
public class RedisConnFactory {

    @Bean("firstRedisConfig")
    @ConfigurationProperties(prefix = "redis.first")
    public RedisConfig firstRedisConfig() {
        return new RedisConfig();
    }


    @Primary
    @Bean("firstLettuceConnectionFactory")
    public LettuceConnectionFactory firstLettuceConnectionFactory(@Qualifier("firstRedisConfig") RedisConfig config) {
        RedisStandaloneConfiguration cfg = new RedisStandaloneConfiguration(config.getHost(), config.getPort());
        if (StringUtils.hasLength(config.getPassword())) {
            cfg.setPassword(config.getPassword());
        }
        return new LettuceConnectionFactory(cfg);
    }

    @Bean("firstStringRedisTemplate")
    public StringRedisTemplate firstStringRedisTemplate(@Qualifier("firstLettuceConnectionFactory") LettuceConnectionFactory lettuceConnectionFactory) {
        return new StringRedisTemplate(lettuceConnectionFactory);
    }


    @Bean("secondRedisConfig")
    @ConfigurationProperties(prefix = "redis.second")
    public RedisConfig secondRedisConfig() {
        return new RedisConfig();
    }


    @Bean("secondLettuceConnectionFactory")
    public LettuceConnectionFactory secondLettuceConnectionFactory(@Qualifier("secondRedisConfig") RedisConfig config) {
        RedisStandaloneConfiguration cfg = new RedisStandaloneConfiguration(config.getHost(), config.getPort());
        if (StringUtils.hasLength(config.getPassword())) {
            cfg.setPassword(config.getPassword());
        }
        return new LettuceConnectionFactory(cfg);
    }

    @Bean("secondStringRedisTemplate")
    public StringRedisTemplate secondStringRedisTemplate(@Qualifier("secondLettuceConnectionFactory") LettuceConnectionFactory lettuceConnectionFactory) {
        return new StringRedisTemplate(lettuceConnectionFactory);
    }

}

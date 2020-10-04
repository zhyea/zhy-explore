package org.chobit.spring.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;


@Component
public class RedisClient {

    @Autowired
    private StringRedisTemplate redisTemplate;


    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }


    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }


    public String hget(String key, String field) {
        if (redisTemplate.opsForHash().hasKey(key, field)) {
            Object obj = redisTemplate.opsForHash().get(key, field);
            if (null != obj) {
                return obj.toString();
            }
        }
        return null;
    }
}

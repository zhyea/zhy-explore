package org.chobit.spring.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


@Component
public class RedisClient {

    @Autowired
    private StringRedisTemplate redisTemplate;


    public String get(String key) {
        return String.valueOf(redisTemplate.opsForValue().get(key));
    }


    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }


    public void set(String key, String value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }


    public Boolean setIfAbsent(String key, String value, Duration timeout) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, timeout);
    }


    public Boolean delete(String key){
        return redisTemplate.delete(key);
    }



    public String hGet(String key, String field) {
        if (redisTemplate.opsForHash().hasKey(key, field)) {
            Object obj = redisTemplate.opsForHash().get(key, field);
            if (null != obj) {
                return obj.toString();
            }
        }
        return null;
    }


    public void hSet(String key, String field, String value) {
        redisTemplate.opsForHash().put(key, field, value);
    }


    public Long ttl(String key, TimeUnit timeUnit) {
        return redisTemplate.getExpire(key, timeUnit);
    }


    public List<String> keys(String pattern) {
        ScanOptions ops = ScanOptions.scanOptions().match(pattern).count(100).build();

        RedisConnection conn = Objects.requireNonNull(redisTemplate.getConnectionFactory()).getConnection();
        Cursor<byte[]> c = conn.scan(ops);

        List<String> list = new LinkedList<>();
        while (c.hasNext()) {
            byte[] bytes = c.next();
            list.add(new String(bytes));
        }
        return list;
    }



    public Boolean expire(String key, long timeout, TimeUnit timeUnit) {
        return redisTemplate.expire(key, timeout, timeUnit);
    }


    public Boolean expireAt(String key, Date date) {
        return redisTemplate.expireAt(key, date);
    }


    public void multiSetBit(final String key, boolean value, int... offsets) {
        redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
            byte[] bytes = key.getBytes();
            for (long offset : offsets) {
                connection.setBit(bytes, offset, value);
            }
            return null;
        });
    }


    public List<Boolean> multiGetBit(String name, int... offsets) {
        List<Object> results = redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
            for (long offset : offsets) {
                connection.getBit(name.getBytes(), offset);
            }
            return null;
        });

        List<Boolean> list = new LinkedList<>();
        results.forEach(obj -> list.add((Boolean) obj));

        return list;
    }
}

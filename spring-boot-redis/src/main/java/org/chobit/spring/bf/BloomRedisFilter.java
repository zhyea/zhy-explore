package org.chobit.spring.bf;

import org.chobit.spring.cache.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class BloomRedisFilter {

    @Autowired
    private RedisClient redisClient;

    private final BloomFilterHelper bloomFilterHelper = new BloomFilterHelper(10000 * 10000, 0.001);


    public boolean check(String value) {
        String key = new SimpleDateFormat("yyyyMMdd").format(new Date());
        int[] offsets = bloomFilterHelper.murmurHashOffset(value);
        return check0(key, offsets);
    }


    public boolean check0(String key, int[] offsets) {
        List<Boolean> list = redisClient.multiGetBit(key, offsets);
        for (Boolean b : list) {
            if (!b) {
                return false;
            }
        }
        return true;
    }


    public boolean checkAndSet(String value) {
        String key = new SimpleDateFormat("yyyyMMdd").format(new Date());
        long ttl = redisClient.ttl(key, TimeUnit.MILLISECONDS);
        if (ttl < 0) {
            redisClient.expire(key, 1, TimeUnit.DAYS);
        }
        int[] offsets = bloomFilterHelper.murmurHashOffset(value);
        boolean r = check0(key, offsets);
        redisClient.multiSetBit(key, true, offsets);
        return r;
    }

}

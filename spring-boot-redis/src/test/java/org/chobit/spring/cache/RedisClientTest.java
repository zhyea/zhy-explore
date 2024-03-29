package org.chobit.spring.cache;

import org.chobit.spring.TestBase;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RedisClientTest extends TestBase {


    @Autowired
    private RedisClient client;


    @Test
    public void get() {
        client.set("zhy", "123");
        String v = client.get("zhy");
        Assert.assertEquals("123", v);
    }


    @Test
    public void keys() {
        String pattern = "";
        List<String> keys = client.keys(pattern);
        System.out.println(keys);
    }


    @Test
    public void increment(){
        String key = "abc123-de";
        client.increment(key);
        client.increment(key);
        client.increment(key);
        String v = client.get(key);
        System.out.println(v);
    }

}

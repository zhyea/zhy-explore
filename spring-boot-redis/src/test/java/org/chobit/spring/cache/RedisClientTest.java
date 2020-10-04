package org.chobit.spring.cache;

import org.chobit.spring.TestBase;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RedisClientTest extends TestBase {


    @Autowired
    private RedisClient client;


    @Test
    public void get() {
        client.set("zhy", "123");
        String v = client.get("zhy");
        Assert.assertEquals("123", v);
    }

}

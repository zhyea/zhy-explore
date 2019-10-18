package org.chobit.spring.config;

import org.chobit.spring.TestBase;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomConfigTest extends TestBase {


    @Autowired
    private CustomConfig config;

    @Test
    public void config() {
        Assert.assertNotNull(config);
        Assert.assertEquals(128, config.getId());
        Assert.assertNotNull(config.getWhiteUrl());
        Assert.assertEquals(2, config.getWhiteUrl().size());
        Assert.assertNotNull(config.getWorker());
        Assert.assertEquals("zhyeeeeeeee", config.getToken());
    }

}

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
    }

    @Test
    public void profile() {
        Assert.assertEquals("dev", config.getProfile());
    }


    @Test
    public void id() {
        Assert.assertEquals(126, config.getId());
    }

    @Test
    public void token() {
        Assert.assertEquals("zhyeeeeeeee", config.getToken());
    }

    @Test
    public void seq() {
        Assert.assertEquals("11112222", config.getSeq());
    }

    @Test
    public void whiteUrl() {
        Assert.assertEquals(2, config.getWhiteUrl().size());
    }

    @Test
    public void blackUrl() {
        Assert.assertEquals(2, config.getBlackUrl().size());

    }

    @Test
    public void worker1() {
        Assert.assertNotNull(config.getWorker1());
        Assert.assertEquals("robin", config.getWorker1().getName());
    }

    @Test
    public void worker2() {
        Assert.assertNotNull(config.getWorker2());
        Assert.assertEquals("jane", config.getWorker2().getName());
    }

    @Test
    public void worker3() {
        Assert.assertNotNull(config.getWorker3());
        Assert.assertEquals("robin", config.getWorker3().getName());
    }

    @Test
    public void content() {
        System.out.println(config.getContent());
        Assert.assertEquals("line \\n next line", config.getContent());
    }

}

package org.chobit.spring.web;

import org.chobit.spring.ApiTestBase;
import org.chobit.spring.bean.Worker;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class WorkerApiTest extends ApiTestBase {

    @Override
    protected String parentPath() {
        return "/api/worker";
    }


    @Test
    public void add() {
        Map<String, Object> param = new HashMap<>(2);
        param.put("name", "zhyea.com");
        param.put("age", 5);

        Integer id = testPost("", param, Integer.class);
        Assert.assertEquals(9, id.intValue());
    }


    @Test
    public void update() {
        Map<String, Object> param = new HashMap<>(2);
        param.put("id", 9);
        param.put("name", "chobit.org");
        param.put("age", 5);

        Boolean r = testPut("", param, Boolean.class);
        Assert.assertTrue(r);
    }


    @Test
    public void get() {
        Worker w = testGet("/1", Worker.class);
        Assert.assertEquals(33, w.getAge());
    }


    @Test
    public void delete() {
        Boolean r = testDelete("/9", Boolean.class);
        Assert.assertTrue(r);
    }


}

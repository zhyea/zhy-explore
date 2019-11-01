package org.chobit.spring.service;

import org.chobit.spring.TestBase;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class WorkerServiceTest extends TestBase {

    @Autowired
    private WorkerService workerService;

    @Test
    public void getName() {
        String name = workerService.getName();
        Assert.assertEquals("Jim", name);
    }

    @Test
    public void getSPELToken() {
        System.out.println(workerService.spelToken());
    }


}

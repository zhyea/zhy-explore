package org.chobit.spring.service;

import org.chobit.spring.TestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

public class TaskServiceTest extends TestBase {


    @Autowired
    private TaskService taskService;

    @Test
    public void job() throws InterruptedException {
        taskService.job();
        TimeUnit.MINUTES.sleep(10);
    }
}

package org.chobit.spring.tools;


import org.chobit.spring.bean.Worker;
import org.chobit.spring.service.MasterService;
import org.chobit.spring.service.WorkerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MyAppTest {

    @Autowired
    private WorkerService workerService;
    @Autowired
    private MasterService masterService;

    @Test
    public void get() throws InterruptedException {
        Worker worker = null;

        worker = workerService.get(2);
        System.out.println(worker);
        worker = workerService.get(2);
        System.out.println(worker);
        // sleep后会执行方法
        TimeUnit.MINUTES.sleep(1L);
        worker = workerService.get(2);
        System.out.println(worker);

        worker = masterService.get(1);
        System.out.println(worker);
        worker = masterService.get(1);
        System.out.println(worker);
        // sleep后不会执行方法，依然从缓存中取值
        TimeUnit.MINUTES.sleep(1L);
        worker = masterService.get(1);
        System.out.println(worker);
    }

}

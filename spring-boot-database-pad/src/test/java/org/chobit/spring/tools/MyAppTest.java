package org.chobit.spring.tools;


import org.chobit.common.concurrent.Threads;
import org.chobit.spring.entity.Worker;
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

    @Test
    public void getWorker() {
        workerService.create();
        System.out.println("=================>>>>> insert start");
        workerService.insert("robin");
        System.out.println("=================>>>>> insert end");
        Worker worker = workerService.get(1);
        System.out.println(worker);
    }


    @Test
    public void update() {
        workerService.create();
        int idVal = workerService.insert("robin");
        int id = (0 == idVal ? 1 : idVal);

        Threads.newThread(() -> {
            this.workerService.updateAge(id, 16);
        }, "updateAge", false).start();

        Threads.newThread(() -> {
            this.workerService.updateName(id, "tome");
        }, "updateName", false).start();

        Threads.sleep(TimeUnit.SECONDS, 12);

        Worker worker = workerService.get(1);
        System.out.println(worker);
    }

}

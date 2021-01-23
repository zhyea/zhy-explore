package org.chobit.spring.tools;


import org.chobit.spring.entity.Worker;
import org.chobit.spring.service.WorkerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MyAppTest {

    @Autowired
    private WorkerService workerService;

    @Test
    public void getWorker() {
        workerService.create();
        workerService.insert(null);
        Worker worker = workerService.get(1);
        System.out.println(worker);
    }


}

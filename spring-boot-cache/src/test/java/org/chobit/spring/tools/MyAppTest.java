package org.chobit.spring.tools;


import org.chobit.spring.bean.Worker;
import org.chobit.spring.service.MasterService;
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
    @Autowired
    private MasterService masterService;

    @Test
    public void get() throws InterruptedException {
        Worker worker = null;

        worker = workerService.get(2);
        System.out.println(worker);
        worker = workerService.get(2);
        System.out.println(worker);
        worker = workerService.get(2);
        System.out.println(worker);

        worker = masterService.get(1);
        System.out.println(worker);
        worker = masterService.get(1);
        System.out.println(worker);
    }

}

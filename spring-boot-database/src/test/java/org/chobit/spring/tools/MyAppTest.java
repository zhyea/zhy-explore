package org.chobit.spring.tools;


import org.chobit.spring.entity.Master;
import org.chobit.spring.service.MasterService;
import org.chobit.spring.service.WorkerService;
import org.junit.Assert;
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
    public void getWorker() {
        workerService.create();
        workerService.get(1);
    }


    @Test
    public void getMaster() {
        masterService.create();
        masterService.insert();
        Master m = masterService.get(1);
        System.out.println(m);
    }


    @Test
    public void getMaster2() {
        masterService.create();
        try {
            masterService.get0();
        } catch (Exception e) {
            System.out.println("------ occurred error: " + e.getMessage());
        }
        masterService.insert();
        Assert.assertNull(masterService.get(1));
        Assert.assertNotNull(masterService.get(2));
    }

}

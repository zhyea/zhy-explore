package org.chobit.spring.service;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class TaskService {

    //@Autowired
    private TaskScheduler taskScheduler;

    public void job() {
        int fixRate = 10;
        taskScheduler.schedule(() -> System.out.println("  job4 ----- " + System.currentTimeMillis()),
                new PeriodicTrigger(fixRate, TimeUnit.SECONDS));
    }

}

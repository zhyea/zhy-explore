package org.chobit.spring.task;


import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.stereotype.Component;


@QuartzDataSource
@Component
public class ScheduledTask {

    //@Async
    //@Scheduled(fixedRate = 1000)
    public void job1() {
        System.out.println(Thread.currentThread().getId() + " ----- job1 ----- " + System.currentTimeMillis());
    }

    //@Async
    //@Scheduled(fixedDelay = 1000)
    public void job2() {
        System.out.println(Thread.currentThread().getId() + " ----- job2 ----- " + System.currentTimeMillis());
    }

    //@Async
    //@Scheduled(fixedRate = 1000)
    public void job3() {
        System.out.println(Thread.currentThread().getId() + " ----- job3 ----- " + System.currentTimeMillis());
    }


}

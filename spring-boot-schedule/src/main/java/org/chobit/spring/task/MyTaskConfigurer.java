package org.chobit.spring.task;

import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

//@Configuration
public class MyTaskConfigurer implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix("my-task-scheduler");
        scheduler.setPoolSize(10);
        scheduler.initialize();
        taskRegistrar.setTaskScheduler(scheduler);

        taskRegistrar
                .addCronTask(
                        () -> System.out.println(Thread.currentThread().getId() + " --- job5 ----- " + System.currentTimeMillis()),
                        "0/1 * * * * ?"
                );

        taskRegistrar
                .addFixedDelayTask(
                        () -> System.out.println(Thread.currentThread().getId() + " --- job6 ----- " + System.currentTimeMillis()),
                        1000
                );

        taskRegistrar
                .addFixedRateTask(
                        () -> System.out.println(Thread.currentThread().getId() + " --- job7 ----- " + System.currentTimeMillis()),
                        1000
                );
    }
}

package org.chobit.quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

public class JobClient {

    public static void main(String[] args) throws SchedulerException {
        MyConfig config = new MyConfig(1);
        JobRegistry registry = new JobRegistry(config);

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.setJobFactory(new MyJobFactory(registry));

        scheduler.start();

        for(AbstractJob job : registry.jobs()){
            scheduler.scheduleJob(job.job(), job.trigger());
        }
    }
}

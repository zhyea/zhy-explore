package org.chobit.quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

public class JobClient {

    public static void main(String[] args) throws SchedulerException {
        MyConfig config = new MyConfig(1);
        JobProxy proxy = new JobProxy(config);

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.setJobFactory(new MyJobFactory(proxy));

        scheduler.start();

        for(AbstractJob job : proxy.jobs()){
            scheduler.scheduleJob(job.job(), job.trigger());
        }
    }
}

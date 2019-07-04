package org.chobit.quartz;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Trigger;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public abstract class AbstractJob implements Job {

    protected MyConfig config;

    public AbstractJob(MyConfig config) {
        this.config = config;
    }

    public abstract String identity();

    public abstract int intervalSeconds();


    public final JobDetail job() {
        return newJob(this.getClass())
                .withIdentity(identity())
                .build();
    }


    public final Trigger trigger() {
        return newTrigger()
                .withIdentity(identity())
                .startNow()
                .withSchedule(simpleSchedule().withIntervalInSeconds(intervalSeconds()).repeatForever())
                .build();
    }
}

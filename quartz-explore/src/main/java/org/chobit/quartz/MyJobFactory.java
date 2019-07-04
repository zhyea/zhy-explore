package org.chobit.quartz;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyJobFactory implements JobFactory {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final JobProxy jobProxy;

    public MyJobFactory(JobProxy jobProxy) {
        this.jobProxy = jobProxy;
    }

    @Override
    public Job newJob(TriggerFiredBundle bundle, Scheduler scheduler) throws SchedulerException {

        JobDetail jobDetail = bundle.getJobDetail();
        Class<? extends Job> jobClass = jobDetail.getJobClass();
        try {
            if (log.isDebugEnabled()) {
                log.debug(
                        "Producing instance of Job '" + jobDetail.getKey() +
                                "', class=" + jobClass.getName());
            }
            if (AbstractJob.class.isAssignableFrom(jobClass)) {
                return jobProxy.getInstance((Class<? extends AbstractJob>) jobClass);
            }

            return jobClass.newInstance();
        } catch (Exception e) {
            SchedulerException se = new SchedulerException(
                    "Problem instantiating class '"
                            + jobDetail.getJobClass().getName() + "'", e);
            throw se;
        }
    }

}

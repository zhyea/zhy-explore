package org.chobit.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJob extends AbstractJob {


    public MyJob(MyConfig config) {
        super(config);
    }

    @Override
    public String identity() {
        return "MyJob";
    }

    @Override
    public int intervalSeconds() {
        return config.getIntervalSeconds();
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println(context.getJobDetail().getKey());
        System.out.println("------------------------------------------------------");
    }
}

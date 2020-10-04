package org.chobit.spring.task;


import org.chobit.spring.quartz.MyQuartzJob;
import org.quartz.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.UUID;

@Configuration
public class QuartzConfig implements InitializingBean {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;


    @Override
    public void afterPropertiesSet() throws Exception {
        config();
    }


    private void config() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        JobDetail jobDetail = buildJobDetail();
        Trigger trigger = buildJobTrigger(jobDetail);
        scheduler.scheduleJob(jobDetail, trigger);
    }


    private JobDetail buildJobDetail() {
        // 用来存储交互信息
        JobDataMap dataMap = new JobDataMap();
        dataMap.put("key", "zhyea.com");

        return JobBuilder.newJob(MyQuartzJob.class)
                .withIdentity(UUID.randomUUID().toString(), "chobit-job")
                .usingJobData(dataMap)
                .build();
    }


    private Trigger buildJobTrigger(JobDetail jobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "chobit-trigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ?"))
                .build();
    }
}

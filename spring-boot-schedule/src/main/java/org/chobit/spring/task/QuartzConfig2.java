package org.chobit.spring.task;


import org.chobit.spring.quartz.MyQuartzJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig2 {


    @Bean
    public JobDetail buildJobDetail() {
        // 用来存储交互信息
        JobDataMap dataMap = new JobDataMap();
        dataMap.put("key", "zhyea.com");

        return JobBuilder.newJob(MyQuartzJob.class)
                .withIdentity("my-chobit-job")
                .usingJobData(dataMap)
                .build();
    }

    @Bean
    public Trigger buildJobTrigger() {
        return TriggerBuilder.newTrigger()
                .withIdentity("my-chobit-job")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ?"))
                .build();
    }
}

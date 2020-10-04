package org.chobit.spring.task;


import org.chobit.spring.quartz.MyQuartzJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;


/**
 * 这种方案不好，极其无聊
 */
//@Configuration
public class QuartzConfig2 {


    @Bean
    public JobDetail buildJobDetail() {
        // 用来存储交互信息
        JobDataMap dataMap = new JobDataMap();
        dataMap.put("key", "zhyea.com");

        return JobBuilder.newJob(MyQuartzJob.class)
                .withIdentity("my-chobit-job")
                .usingJobData(dataMap)
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger buildJobTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(buildJobDetail())
                .withIdentity("my-chobit-job")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ?"))
                .build();
    }
}

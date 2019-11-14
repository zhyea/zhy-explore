package org.chobit.spring.quartz;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class MyQuartzJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) {
        JobDataMap map = context.getMergedJobDataMap();
        // 从作业上下文中取出Key
        String key = map.getString("key");
        System.out.println(Thread.currentThread().getId() + " -- job8 ---------------------->>>>" + key);
    }

}

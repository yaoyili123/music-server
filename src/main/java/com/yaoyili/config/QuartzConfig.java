package com.yaoyili.config;

import com.yaoyili.tasks.UpdatePlayJob;
import com.yaoyili.tasks.UpdateRankJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail UpdateRankJobDetail(){
        return JobBuilder.newJob(UpdateRankJob.class)
                .withIdentity("UpdateRank")   //job id
                //每个JobDetail内都有一个Map，包含了关联到这个Job的数据，在Job类中可以通过context获取
                .usingJobData("msg", "execute UpdateRankJob")//关联键值对
                .storeDurably()//即使没有Trigger关联时，也不需要删除该JobDetail
                .build();
    }

    @Bean
    public Trigger UpdateRankJobTrigger() {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("*/10 * * * * ?");
        return TriggerBuilder.newTrigger()
                .forJob(UpdateRankJobDetail())//关联上述的JobDetail
                .withIdentity("UpdateRankJob")//给Trigger起个名字
                .withSchedule(cronScheduleBuilder)
                .build();
    }

    @Bean
    public JobDetail UpdatePlayJobDetail(){
        return JobBuilder.newJob(UpdatePlayJob.class)
                .withIdentity("UpdatePlay")   //job id
                //每个JobDetail内都有一个Map，包含了关联到这个Job的数据，在Job类中可以通过context获取
                .usingJobData("msg", "execute UpdatePlayJob")//关联键值对
                .storeDurably()//即使没有Trigger关联时，也不需要删除该JobDetail
                .build();
    }

    @Bean
    public Trigger UpdatePlayJobTrigger() {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("*/1 * * * * ?");
        return TriggerBuilder.newTrigger()
                .forJob(UpdatePlayJobDetail())//关联上述的JobDetail
                .withIdentity("UpdatePlayJob")//给Trigger起个名字
                .withSchedule(cronScheduleBuilder)
                .build();
    }
}
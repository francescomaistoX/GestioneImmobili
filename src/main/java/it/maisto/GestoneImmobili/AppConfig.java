package it.maisto.GestoneImmobili;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
@Configuration
public class AppConfig {

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob(AffittoJob.class)
                .withIdentity("affittoJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger trigger(JobDetail jobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity("affittoTrigger")
                .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(0, 0)) // Esegui ogni giorno a mezzanotte
                .build();
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(Trigger trigger, JobDetail jobDetail) {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobDetails(jobDetail);
        schedulerFactoryBean.setTriggers(trigger);
        return schedulerFactoryBean;
    }
}

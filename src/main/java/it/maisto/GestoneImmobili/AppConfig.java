package it.maisto.GestoneImmobili;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
@Configuration
@ComponentScan(basePackages = "it.maisto.GestoneImmobili")
@EnableJpaRepositories(basePackages = "it.maisto.GestoneImmobili.repository")
public class AppConfig {
    @Bean
    public AutowiringSpringBeanJobFactory springBeanJobFactory() {
        return new AutowiringSpringBeanJobFactory();
    }

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
                .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(23, 00)) // Esegui ogni giorno a mezzanotte
                .build();
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(Trigger trigger, JobDetail jobDetail, AutowiringSpringBeanJobFactory jobFactory) {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobFactory(jobFactory);
        schedulerFactoryBean.setJobDetails(jobDetail);
        schedulerFactoryBean.setTriggers(trigger);
        return schedulerFactoryBean;
    }
}

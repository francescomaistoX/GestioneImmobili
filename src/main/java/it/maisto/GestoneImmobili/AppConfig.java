package it.maisto.GestoneImmobili;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.Properties;

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

    @Bean
    public JavaMailSenderImpl getJavaMailSender(@Value("${gmail.mail.transport.protocol}" )String protocol,
                                                @Value("${gmail.mail.smtp.auth}" ) String auth,
                                                @Value("${gmail.mail.smtp.starttls.enable}" )String starttls,
                                                @Value("${gmail.mail.debug}" )String debug,
                                                @Value("${gmail.mail.from}" )String from,
                                                @Value("${gmail.mail.from.password}" )String password,
                                                @Value("${gmail.smtp.ssl.enable}" )String ssl,
                                                @Value("${gmail.smtp.host}" )String host,
                                                @Value("${gmail.smtp.port}" )String port){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(Integer.parseInt(port));

        mailSender.setUsername(from);
        mailSender.setPassword(password);


        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", protocol);
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.starttls.enable", starttls);
        props.put("mail.debug", debug);
        props.put("smtp.ssl.enable",ssl);

        return mailSender;

    }

    @Bean
    public JobDetail avvisoAffittoJobDetail() {
        return JobBuilder.newJob(AvvisoScadenzaAffitto.class)
                .withIdentity("avvisoAffittoJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger avvisoAffittoTrigger(JobDetail avvisoAffittoJobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(avvisoAffittoJobDetail)
                .withIdentity("avvisoAffittoTrigger")
                .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(10, 45)) // Ogni giorno alle 8 del mattino
                .build();
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBeanEmail(Trigger avvisoAffittoTrigger, JobDetail avvisoAffittoJobDetail, AutowiringSpringBeanJobFactory jobFactory) {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobFactory(jobFactory);
        schedulerFactoryBean.setJobDetails(avvisoAffittoJobDetail);
        schedulerFactoryBean.setTriggers(avvisoAffittoTrigger);
        return schedulerFactoryBean;
    }
}

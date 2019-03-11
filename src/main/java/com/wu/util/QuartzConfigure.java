package com.wu.util;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class QuartzConfigure {

    // 配置文件路径
    private static final String QUARTZ_CONFIG = "/quartz.properties";
    @Resource
    private DataSource dataSource;

    @Value("${quartz.cronExpression}")
    private String cronExpression;

    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource(QUARTZ_CONFIG));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {
        AutoWiredSpringBeanToJobFactory jobFactory = new AutoWiredSpringBeanToJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }
    @Bean
    public SchedulerFactoryBean schedulerFactory(JobFactory jobFactory, Trigger... cronJobTrigger) throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setJobFactory(jobFactory);
        factory.setOverwriteExistingJobs(true);
        // 设置自行启动
        factory.setAutoStartup(true);
        factory.setQuartzProperties(quartzProperties());
        //factory.setTriggers(cronJobTrigger);
        factory.setDataSource(dataSource);
        // 使用应用的dataSource替换quartz的dataSource
        return factory;
    }

    @Bean
    public CronTriggerFactoryBean cronJobTrigger(JobDetail jobDetail) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(jobDetail);
        //延迟启动
        trigger.setStartDelay(2000);
        //从application.yml文件读取
        trigger.setCronExpression(cronExpression);
        return trigger;
    }

    @Bean
    public JobDetailFactoryBean jobDetail() {
        //集群模式下必须使用JobDetailFactoryBean，
        // MethodInvokingJobDetailFactoryBean 类中的 methodInvoking 方法，
        // 是不支持序列化的
        JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
        jobDetail.setDurability(true);
        jobDetail.setRequestsRecovery(true);
        jobDetail.setJobClass(MyJobDetail.class);
        return jobDetail;
    }
    @Bean(name = "scheduler")
    public Scheduler scheduler(SchedulerFactoryBean schedulerFactory) throws IOException {
        return schedulerFactory.getScheduler();
    }
}

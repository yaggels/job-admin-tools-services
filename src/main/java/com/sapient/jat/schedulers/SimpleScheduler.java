package com.sapient.jat.schedulers;

import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import com.sapient.jat.config.AutoWiringSpringBeanJobFactory;
import com.sapient.jat.jobs.HelloWorldJob;
import com.sapient.jat.jobs.SimpleJob;

@Configuration
public class SimpleScheduler {

	Logger logger = LoggerFactory.getLogger(SimpleScheduler.class);

	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	public SpringBeanJobFactory springBeanJobFactory() {
		AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();
		logger.debug("Configuring Job factory");

		jobFactory.setApplicationContext(applicationContext);
		return jobFactory;
	}

	@Bean
	public SchedulerFactoryBean scheduler(Trigger cronTrigger, Trigger trigger) {

		SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
		schedulerFactory.setConfigLocation(new ClassPathResource("quartz.properties"));

		logger.debug("Setting the Scheduler up");
		schedulerFactory.setJobFactory(springBeanJobFactory());
		schedulerFactory.setTriggers(cronTrigger, trigger);
		
		return schedulerFactory;
	}

	@Bean
	public JobDetailFactoryBean simpleJobDetail() {

		JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();

		jobDetailFactory.setJobClass(SimpleJob.class);
		jobDetailFactory.setName("Simple_Job");
		jobDetailFactory.setDescription("Work Simeple Job ...");
		jobDetailFactory.setDurability(true);

		return jobDetailFactory;
	}
	
	@Bean
	public JobDetailFactoryBean helloWorldJobDetail() {
		JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();

		jobDetailFactory.setJobClass(HelloWorldJob.class);
		jobDetailFactory.setName("HelloWorld_Job");
		jobDetailFactory.setDescription("Hello World Job ...");
		jobDetailFactory.setDurability(true);

		return jobDetailFactory;
	}

	@Bean
	public SimpleTriggerFactoryBean trigger(JobDetail simpleJobDetail) {

		SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
		trigger.setJobDetail(simpleJobDetail);

		int frequencyInSec = 10;
		logger.info("Configuring trigger to fire every {} seconds", frequencyInSec);

		trigger.setRepeatInterval(frequencyInSec * 1000);
		trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
		trigger.setName("Simple_Trigger_10");
		return trigger;
	}
	
	@Bean
	public CronTriggerFactoryBean cronTrigger(JobDetail helloWorldJobDetail) {
		CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
		
		trigger.setJobDetail(helloWorldJobDetail);
		trigger.setCronExpression("0/30 0/1 * 1/1 * ? *");
		trigger.setName("Cron_Trigger_30");
		
		return trigger;
	}

}

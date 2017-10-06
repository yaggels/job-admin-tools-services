package com.sapient.jat.schedulers;

import org.quartz.JobDataMap;
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
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import com.sapient.jat.config.AutoWiringSpringBeanJobFactory;
import com.sapient.jat.jobs.HelloWorldJob;

@Configuration
public class SimpleScheduler {

	private Logger logger = LoggerFactory.getLogger(SimpleScheduler.class);
	private final String SCHEDULER_NAME = "TIMS_SCHEDULER";

	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	public SpringBeanJobFactory springBeanJobFactory() {
		AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();
		
		jobFactory.setApplicationContext(applicationContext);
		return jobFactory;
	}

	@Bean
	public SchedulerFactoryBean scheduler(Trigger helloWorldSimpleTrigger) {

		SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
		schedulerFactory.setConfigLocation(new ClassPathResource("quartz.properties"));

		logger.debug("Setting the Scheduler up");
		schedulerFactory.setJobFactory(springBeanJobFactory());
		schedulerFactory.setSchedulerName(SCHEDULER_NAME);
		schedulerFactory.setTriggers(helloWorldSimpleTrigger);
		
		return schedulerFactory;
	}

	@Bean
	public JobDetailFactoryBean helloWorldJobDetail() {
		JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();

		jobDetailFactory.setJobClass(HelloWorldJob.class);
		jobDetailFactory.setName("HelloWorld_Job");
		jobDetailFactory.setDescription("Job that says Hello World");
		
		JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put("greeting", "Hello friend");
		jobDetailFactory.setJobDataMap(jobDataMap);
		
		return jobDetailFactory;
	}
	
	@Bean
	public SimpleTriggerFactoryBean helloWorldSimpleTrigger(JobDetail helloWorldJobDetail) {

		SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
		trigger.setJobDetail(helloWorldJobDetail);
		trigger.setRepeatInterval(10 * 1000); //10 seconds
		trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
		trigger.setName("HelloWorldJob_Simple_Trigger");
		
		return trigger;
	}
}

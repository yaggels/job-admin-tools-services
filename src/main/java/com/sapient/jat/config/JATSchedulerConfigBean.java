package com.sapient.jat.config;

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

import com.sapient.jat.jobs.HelloWorldJob;

/**
 * Simple configuration bean responsible for registering jobs and their initial
 * schedule
 * 
 * @author Johnson Chow
 *
 */
@Configuration
public class JATSchedulerConfigBean {

	private Logger logger = LoggerFactory.getLogger(JATSchedulerConfigBean.class);

	@Autowired
	private ApplicationContext applicationContext;

	/**
	 * Registers a new instance of the Auto Wiring Spring Bean job factory that
	 * accepts Quartz jobs
	 * 
	 * @return an instance of the SpringBeanJobFactory
	 */
	@Bean
	public SpringBeanJobFactory springBeanJobFactory() {
		AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();

		jobFactory.setApplicationContext(applicationContext);
		return jobFactory;
	}

	/**
	 * Responsible for registering and scheduling jobs
	 * 
	 * @param helloWorldSimpleTrigger
	 * @return an instance of the SchedulerFactoryBean
	 */
	@Bean
	public SchedulerFactoryBean scheduler(Trigger helloWorldSimpleTrigger) {

		SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
		schedulerFactory.setConfigLocation(new ClassPathResource("quartz.properties"));

		logger.debug("Setting the Scheduler up");
		schedulerFactory.setJobFactory(springBeanJobFactory());
		schedulerFactory.setTriggers(helloWorldSimpleTrigger);

		return schedulerFactory;
	}

	/**
	 * Defines a simple Hello World Job via Spring bean style definition.
	 * 
	 * @return an instance of the HelloWordJobDetails
	 */
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

	/**
	 * Defines a simple trigger that repeats every 10 seconds forever. Takes in a
	 * job detail and schedule it as such.
	 * 
	 * @param helloWorldJobDetail
	 *            takes this job and schedule it based on the trigger definition
	 * @return an instance of the HelloWorldSimpleTrigger
	 */
	@Bean
	public SimpleTriggerFactoryBean helloWorldSimpleTrigger(JobDetail helloWorldJobDetail) {

		SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
		trigger.setJobDetail(helloWorldJobDetail);
		trigger.setRepeatInterval(10 * 1000); // 10 seconds
		trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
		trigger.setName("HelloWorldJob_Simple_Trigger");
		// trigger.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);

		return trigger;
	}
}

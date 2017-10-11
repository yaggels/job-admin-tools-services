package com.sapient.jat.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * Simple REST UI controller to prove how to display existing jobs.
 * 
 * @author Johnson Chow
 *
 */
@RestController
public class SimpleController {

	@Autowired
	private ApplicationContext applicationContext;

	@RequestMapping(value = "/getjobs", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<String>> GetScheduledJobs() {
		SchedulerFactoryBean schedulerFactoryBean = applicationContext.getBean(SchedulerFactoryBean.class);
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		List<String> jobDetails = new ArrayList<>();

		try {
			for (String groupName : scheduler.getJobGroupNames()) {

				for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {

					String jobName = jobKey.getName();
					String jobGroup = jobKey.getGroup();

					// get job's trigger
					List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
					Date nextFireTime = triggers.get(0).getNextFireTime();

					jobDetails.add("[jobName] : " + jobName + " [groupName] : " + jobGroup + " - " + nextFireTime);
				}

			}
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<>(jobDetails, HttpStatus.OK);

	}

	@RequestMapping(value = "/hello")
	public String hello() {
		return "Hello!";
	}

}

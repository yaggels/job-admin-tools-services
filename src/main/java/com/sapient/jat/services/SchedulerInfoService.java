package com.sapient.jat.services;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.sapient.jat.domains.SchedulerInfo;

/**
 * 
 * Scheduler interface that provides common tasks available for it.
 * 
 * @author Johnson Chow
 *
 */
@Service
public class SchedulerInfoService {

	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	/**
	 * Retrieves the scheduler information.
	 * 
	 * @return an instance of the scheduler
	 * @throws SchedulerException
	 */
	public SchedulerInfo getSchedulerInfo() throws SchedulerException {

		Scheduler scheduler = schedulerFactoryBean.getScheduler();

		SchedulerInfo schedulerInfo = new SchedulerInfo(scheduler.getSchedulerName(), scheduler.isInStandbyMode(),
				scheduler.isStarted(), scheduler.isShutdown());

		return schedulerInfo;
	}

	/**
	 * Places the scheduler in standby.
	 * 
	 * @throws SchedulerException
	 */
	public void standby() throws SchedulerException {
		schedulerFactoryBean.getScheduler().standby();
	}

	/**
	 * Starts the scheduler.
	 * 
	 * @throws SchedulerException
	 */
	public void start() throws SchedulerException {
		schedulerFactoryBean.getScheduler().start();
	}

	/**
	 * Shuts down the scheduler
	 * 
	 * @throws SchedulerException
	 */
	public void shutdown() throws SchedulerException {
		schedulerFactoryBean.getScheduler().shutdown();
	}

	/**
	 * Temporarily suspends all the triggers registered in the scheduler
	 * 
	 * @throws SchedulerException
	 */
	public void pause() throws SchedulerException {
		schedulerFactoryBean.getScheduler().pauseAll();
	}

	/**
	 * Resume all temporarily suspended triggers registered in the scheduler
	 * 
	 * @throws SchedulerException
	 */
	public void unpause() throws SchedulerException {
		schedulerFactoryBean.getScheduler().resumeAll();
	}
}

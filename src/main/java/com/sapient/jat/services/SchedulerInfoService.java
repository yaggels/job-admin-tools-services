package com.sapient.jat.services;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.sapient.jat.domains.SchedulerInfo;

@Service
public class SchedulerInfoService {

	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	public SchedulerInfo getSchedulerInfo() throws SchedulerException {

		Scheduler scheduler = schedulerFactoryBean.getScheduler();

		SchedulerInfo schedulerInfo = new SchedulerInfo(scheduler.getSchedulerName(), scheduler.isInStandbyMode(),
				scheduler.isStarted(), scheduler.isShutdown());

		return schedulerInfo;
	}
}

package com.sapient.jat.services;

import static org.quartz.impl.matchers.GroupMatcher.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.DateBuilder.*;


import java.util.ArrayList;
import java.util.List;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.sapient.jat.domains.JobInfo;
import com.sapient.jat.domains.ReScheduleDetail;
import com.sapient.jat.domains.TriggerInfo;

@Service
public class TriggerInfoService {
	
	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;
	
	private Scheduler getScheduler() {
		return schedulerFactoryBean.getScheduler();
	}
	
	//TODO builder pattern for TriggerInfo class
	
	public List<TriggerInfo> getTriggers() throws SchedulerException {
		
		List<TriggerInfo> triggers = new ArrayList<>();
			
		// enumerate each trigger group
		for(String group: getScheduler().getJobGroupNames()) {
		    // enumerate each trigger in group
		    for(TriggerKey triggerKey : getScheduler().getTriggerKeys(groupEquals(group))) {
		        
		        Trigger trigger = getScheduler().getTrigger(triggerKey);
		        TriggerInfo triggerInfo = new TriggerInfo(triggerKey.toString(), trigger.getDescription(), trigger.getEndTime(), trigger.getFinalFireTime(), trigger.getNextFireTime(), trigger.getPreviousFireTime());
		        triggerInfo.setStatus(getScheduler().getTriggerState(triggerKey).toString());
		        
		        //Job details
		        JobKey jobKey = trigger.getJobKey();
		        JobDetail jobDetail = getScheduler().getJobDetail(jobKey);
		        JobInfo jobInfo = new JobInfo(jobKey.toString(), jobDetail.getDescription());
		        triggerInfo.setJobInfo(jobInfo);
		        
		        triggers.add(triggerInfo);
		    }
		}
		
		return triggers;
	}
	
	public TriggerInfo getTrigger(String key) throws SchedulerException {
		TriggerKey triggerKey = getTriggerKey(key);
		Trigger trigger = getScheduler().getTrigger(triggerKey);
		TriggerInfo triggerInfo = new TriggerInfo(key, trigger.getDescription(), trigger.getEndTime(), trigger.getFinalFireTime(), trigger.getNextFireTime(), trigger.getPreviousFireTime());
		triggerInfo.setStatus(getScheduler().getTriggerState(triggerKey).toString());
		
		return triggerInfo;
	}
	
	private static TriggerKey getTriggerKey(String key) {
		int indexDelimeterBetGroupAndName = key.indexOf(".");
		return TriggerKey.triggerKey(key.substring(indexDelimeterBetGroupAndName + 1), key.substring(0, indexDelimeterBetGroupAndName));
	}
	
	public void pause(String key) throws SchedulerException {
		TriggerKey triggerKey = getTriggerKey(key);
		getScheduler().pauseTrigger(triggerKey);
	}
	
	public void resume(String key) throws SchedulerException {
		TriggerKey triggerKey = getTriggerKey(key);
		getScheduler().resumeTrigger(triggerKey);
	}
	
	public void runNow(String key) throws SchedulerException {
		TriggerKey triggerKey = getTriggerKey(key);
		Trigger trigger = getScheduler().getTrigger(triggerKey);
		getScheduler().triggerJob(trigger.getJobKey());
	}
	
	public void unschedule(String key) throws SchedulerException {
		TriggerKey triggerKey = getTriggerKey(key);
		getScheduler().unscheduleJob(triggerKey);
	}
	
	public void reschedule(ReScheduleDetail reScheduleDetail) throws SchedulerException {
		TriggerKey triggerKey = getTriggerKey(reScheduleDetail.getTriggerKey());
		
		SimpleTrigger trigger = (SimpleTrigger) newTrigger()
				.withIdentity(triggerKey)
				.startAt(todayAt(reScheduleDetail.getStartHour(), reScheduleDetail.getStartMin(), reScheduleDetail.getStartSec()))
				.endAt(todayAt(reScheduleDetail.getEndHour(), reScheduleDetail.getEndMin(), reScheduleDetail.getEndSec()))
				.withDescription("Test Reschedule")
				.withSchedule(repeatSecondlyForever())
				.build();
		
		getScheduler().rescheduleJob(triggerKey, trigger);
	}
}
 
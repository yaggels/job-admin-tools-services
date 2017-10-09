package com.sapient.jat.ui.resources;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sapient.jat.domains.JobInfo;
import com.sapient.jat.domains.TriggerInfo;

public class TriggerInfoResource extends ResourceSupport {
	
	private final String triggerId;
	private final String description;
	private final LocalDateTime endTime;
	private final LocalDateTime finalFireTime;
	private final LocalDateTime nextFireTime;
	private final LocalDateTime previousFireTime;
	private final String status;
	private final JobInfo jobInfo;
	
	public TriggerInfoResource (TriggerInfo triggerInfo) {
		
		this.triggerId = triggerInfo.getId();
		this.description = triggerInfo.getDescription(); 
		this.endTime = triggerInfo.getEndTime();
		this.finalFireTime = triggerInfo.getFinalFireTime();
		this.nextFireTime = triggerInfo.getNextFireTime();
		this.previousFireTime = triggerInfo.getPreviousFireTime();
		this.status = triggerInfo.getStatus();
		this.jobInfo = triggerInfo.getJobInfo();
	}

	@JsonProperty("id")
	public String getTriggerId() {
		return triggerId;
	}

	public String getDescription() {
		return description;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public LocalDateTime getFinalFireTime() {
		return finalFireTime;
	}

	public LocalDateTime getNextFireTime() {
		return nextFireTime;
	}

	public LocalDateTime getPreviousFireTime() {
		return previousFireTime;
	}

	public String getStatus() {
		return status;
	}

	public JobInfo getJobInfo() {
		return jobInfo;
	}
}

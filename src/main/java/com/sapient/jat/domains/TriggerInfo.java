package com.sapient.jat.domains;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Objects;

import org.springframework.hateoas.Identifiable;

public class TriggerInfo implements Identifiable<String> {
	
	private final String triggerKey;
	private final String description;
	private final LocalDateTime endTime;
	private final LocalDateTime finalFireTime;
	private final LocalDateTime nextFireTime;
	private final LocalDateTime previousFireTime;
	private String status;
	//TODO figure out how to finalize status
	private JobInfo jobInfo;
	
	public TriggerInfo(String triggerKey, String description, Date endTime, Date finalFireTime, Date nextFireTime,
			Date previousFireTime) {
		
		this.triggerKey = triggerKey;
		this.description = description;
		this.endTime = Objects.nonNull(endTime) ? LocalDateTime.ofInstant(endTime.toInstant(), ZoneId.systemDefault()) : LocalDateTime.MAX;
		this.finalFireTime = Objects.nonNull(finalFireTime) ? LocalDateTime.ofInstant(finalFireTime.toInstant(), ZoneId.systemDefault()) : LocalDateTime.MAX;;
		this.nextFireTime = LocalDateTime.ofInstant(nextFireTime.toInstant(), ZoneId.systemDefault());
		this.previousFireTime = LocalDateTime.ofInstant(previousFireTime.toInstant(), ZoneId.systemDefault());
	}
	
	@Override
	public String getId() {
		return getTriggerKey();
	}

	public String getTriggerKey() {
		return triggerKey;
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

	public void setStatus(String status) {
		this.status = status;
	}

	public JobInfo getJobInfo() {
		return jobInfo;
	}

	public void setJobInfo(JobInfo jobInfo) {
		this.jobInfo = jobInfo;
	}
}

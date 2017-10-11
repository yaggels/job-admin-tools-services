package com.sapient.jat.domains;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

import org.springframework.hateoas.Identifiable;

/**
 * Trigger Information POJO that implements the Spring HATEOS Identifiable
 * interface. This helps lay hypermedia controls.
 * 
 * @author Johnson Chow
 *
 */
public class TriggerInfo implements Identifiable<String> {

	private final String triggerKey;
	private final String description;
	private final LocalDateTime endTime;
	private final LocalDateTime finalFireTime;
	private final LocalDateTime nextFireTime;
	private final LocalDateTime previousFireTime;

	// TODO figure out how to finalize status and jobInfo
	private String status;
	private JobInfo jobInfo;

	// TODO refactor to builder constructor
	/**
	 * Default constructor
	 * 
	 * @param triggerKey
	 * @param description
	 * @param endTime
	 * @param finalFireTime
	 * @param nextFireTime
	 * @param previousFireTime
	 */
	public TriggerInfo(String triggerKey, String description, Date endTime, Date finalFireTime, Date nextFireTime,
			Date previousFireTime) {

		this.triggerKey = triggerKey;
		this.description = description;
		this.endTime = Objects.nonNull(endTime) ? LocalDateTime.ofInstant(endTime.toInstant(), ZoneId.systemDefault())
				: LocalDateTime.MAX;
		this.finalFireTime = Objects.nonNull(finalFireTime)
				? LocalDateTime.ofInstant(finalFireTime.toInstant(), ZoneId.systemDefault())
				: LocalDateTime.MAX;
		;
		this.nextFireTime = LocalDateTime.ofInstant(nextFireTime.toInstant(), ZoneId.systemDefault());
		this.previousFireTime = LocalDateTime.ofInstant(previousFireTime.toInstant(), ZoneId.systemDefault());
	}

	@Override
	public String getId() {
		return getTriggerKey();
	}

	/**
	 * 
	 * @return the instance id (key) of the trigger
	 */
	public String getTriggerKey() {
		return triggerKey;
	}

	/**
	 * 
	 * @return the description of the trigger
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @return <code>LocalDateTime</code> representation of the end time of this
	 *         trigger.
	 */
	public LocalDateTime getEndTime() {
		return endTime;
	}

	/**
	 * 
	 * @return <code>LocalDateTime</code> representation of the final time of this
	 *         trigger.
	 */
	public LocalDateTime getFinalFireTime() {
		return finalFireTime;
	}

	/**
	 * 
	 * @return <code>LocalDateTime</code> representation of the next time this
	 *         trigger will execute.
	 */
	public LocalDateTime getNextFireTime() {
		return nextFireTime;
	}

	/**
	 * 
	 * @return <code>LocalDateTime</code> representation of the previous trigger
	 *         execution time.
	 */
	public LocalDateTime getPreviousFireTime() {
		return previousFireTime;
	}

	/**
	 * 
	 * @return status of the trigger.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 
	 * @param status
	 *            of the trigger.
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 
	 * @return job details.
	 */
	public JobInfo getJobInfo() {
		return jobInfo;
	}

	/**
	 * 
	 * @param job
	 *            details.
	 */
	public void setJobInfo(JobInfo jobInfo) {
		this.jobInfo = jobInfo;
	}
}

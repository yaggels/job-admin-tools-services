package com.sapient.jat.domains;

import org.springframework.hateoas.Identifiable;

/**
 * 
 * Job Information POJO that implements the Spring HATEOS Identifiable
 * interface. This helps lay hypermedia controls.
 * 
 * @author Johnson Chow
 *
 */
public class JobInfo implements Identifiable<String> {
	private final String jobKey;
	private final String description;

	/**
	 * Default constructor
	 * 
	 * @param instance
	 *            id of the job.
	 * @param description
	 *            of the job.
	 */
	public JobInfo(String jobKey, String description) {
		super();
		this.jobKey = jobKey;
		this.description = description;
	}

	@Override
	public String getId() {
		return jobKey;
	}

	/**
	 * 
	 * @return the instance id of the job.
	 */
	public String getJobKey() {
		return jobKey;
	}

	/**
	 * 
	 * @return the description of the job
	 */
	public String getDescription() {
		return description;
	}
}

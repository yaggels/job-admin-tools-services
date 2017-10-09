package com.sapient.jat.domains;

import org.springframework.hateoas.Identifiable;

public class JobInfo implements Identifiable<String> {
	private final String jobKey;
	private final String description;
	public JobInfo(String jobKey, String description) {
		super();
		this.jobKey = jobKey;
		this.description = description;
	}
	@Override
	public String getId() {
		return jobKey;
	}
	public String getJobKey() {
		return jobKey;
	}
	public String getDescription() {
		return description;
	}
}

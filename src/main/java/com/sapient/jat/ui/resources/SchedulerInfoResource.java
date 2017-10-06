package com.sapient.jat.ui.resources;

import org.quartz.SchedulerException;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sapient.jat.domains.SchedulerInfo;

public class SchedulerInfoResource extends ResourceSupport {
	
	private final String name;
	private final boolean isInStandbyMode;
	private final boolean isStarted;
	private final boolean isShutdown;
	
	public SchedulerInfoResource(SchedulerInfo scheduler) throws SchedulerException {
		
		this.name = scheduler.getId();
		this.isInStandbyMode = scheduler.isInStandbyMode();
		this.isStarted = scheduler.isStarted();
		this.isShutdown = scheduler.isShutdown();
		
		//TODO figure out how to handle exception here....
	}
	
	@JsonProperty("id")
	public String getName() {
		return name;
	}

	public boolean isInStandbyMode() {
		return isInStandbyMode;
	}

	public boolean isStarted() {
		return isStarted;
	}

	public boolean isShutdown() {
		return isShutdown;
	}
	
}

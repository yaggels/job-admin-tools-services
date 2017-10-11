package com.sapient.jat.ui.resources;

import org.quartz.SchedulerException;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sapient.jat.domains.SchedulerInfo;

/**
 * 
 * Resource counterpart to the SchedulerInfo domain objects. Helps separate the
 * inner service to the UI layer.
 * 
 * @author Johnson Chow
 *
 */
public class SchedulerInfoResource extends ResourceSupport {

	private final String name;
	private final boolean isInStandbyMode;
	private final boolean isStarted;
	private final boolean isShutdown;

	/**
	 * Default constructor
	 * 
	 * @param scheduler
	 * @throws SchedulerException
	 */
	public SchedulerInfoResource(SchedulerInfo scheduler) throws SchedulerException {

		this.name = scheduler.getId();
		this.isInStandbyMode = scheduler.isInStandbyMode();
		this.isStarted = scheduler.isStarted();
		this.isShutdown = scheduler.isShutdown();

		// TODO figure out how to handle exception here....
	}

	@JsonProperty("id")
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return a boolean signifying if the scheduler is in standby mode.
	 */
	public boolean isInStandbyMode() {
		return isInStandbyMode;
	}

	/**
	 * 
	 * @return a boolean signifying if the scheduler is started.
	 */
	public boolean isStarted() {
		return isStarted;
	}

	/**
	 * 
	 * @return a boolean signifying if the scheduler is shut down.
	 */
	public boolean isShutdown() {
		return isShutdown;
	}

}

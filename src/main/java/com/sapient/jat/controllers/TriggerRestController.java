package com.sapient.jat.controllers;

import java.util.Collection;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.jat.domains.ReScheduleDetail;
import com.sapient.jat.domains.TriggerInfo;
import com.sapient.jat.services.TriggerInfoService;
import com.sapient.jat.ui.resources.TriggerInfoResource;
import com.sapient.jat.ui.resources.TriggerInfoResourceAssembler;

/**
 * 
 * <p>
 * REST Controller for the Trigger API.
 * </p>
 * 
 * <p>
 * Collection of API available for maintaining triggers
 * </p>
 * 
 * @author Johnson Chow
 *
 */
@RestController
@ExposesResourceFor(TriggerInfo.class)
@RequestMapping(value = "/triggers", produces = "application/json")
public class TriggerRestController {

	@Autowired
	private TriggerInfoService triggerInfoService;
	@Autowired
	private TriggerInfoResourceAssembler triggerInfoResourceAssembler;

	/**
	 * Retrieves a list of running triggers. Note that this doesn't return a trigger
	 * that has been newly scheduled.
	 * 
	 * @return list of triggers with relevant info
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<TriggerInfoResource>> retrieveTriggers() {

		HttpStatus status = null;
		Collection<TriggerInfo> triggers = null;

		try {
			triggers = triggerInfoService.getTriggers();
			status = HttpStatus.OK;
		} catch (SchedulerException e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<>(triggerInfoResourceAssembler.toResourceCollection(triggers), status);
	}

	/**
	 * Retrieves detail for a specific trigger.
	 * 
	 * @param instance
	 *            id of the trigger being requested.
	 * @return trigger instance with relevant info.
	 */
	@RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
	public ResponseEntity<TriggerInfoResource> retrieveTrigger(@PathVariable String id) {
		HttpStatus status = null;
		TriggerInfo triggerInfo = null;
		try {
			triggerInfo = triggerInfoService.getTrigger(id);
			status = HttpStatus.OK;
		} catch (SchedulerException e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<>(triggerInfoResourceAssembler.toResource(triggerInfo), status);
	}

	/**
	 * Pause a specific trigger.
	 * 
	 * @param instance
	 *            id of the trigger being pause.
	 * @return
	 */
	@RequestMapping(value = "/{id:.+}/pause", method = RequestMethod.POST)
	public ResponseEntity<Void> pause(@PathVariable String id) {
		HttpStatus status = null;

		try {
			triggerInfoService.pause(id);
			status = HttpStatus.ACCEPTED;
		} catch (SchedulerException e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<>(status);
	}

	/**
	 * Resumes a specific trigger
	 * 
	 * @param instance
	 *            id of the trigger being pause.
	 * @return
	 */
	@RequestMapping(value = "/{id:.+}/resume", method = RequestMethod.POST)
	public ResponseEntity<Void> resume(@PathVariable String id) {
		HttpStatus status = null;

		try {
			triggerInfoService.resume(id);
			status = HttpStatus.ACCEPTED;
		} catch (SchedulerException e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<>(status);
	}

	/**
	 * Runs the trigger one time outside the schedule
	 * 
	 * @param instance
	 *            id of the trigger being run once.
	 * @return
	 */
	@RequestMapping(value = "/{id:.+}/runnow", method = RequestMethod.POST)
	public ResponseEntity<Void> runNow(@PathVariable String id) {
		HttpStatus status = null;

		try {
			triggerInfoService.runNow(id);
			status = HttpStatus.ACCEPTED;
		} catch (SchedulerException e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<>(status);
	}

	/**
	 * Remove the trigger from the scheduler lifecycle. Once trigger has been
	 * remove, there is no way to resume the job without doing a hard restart.
	 * 
	 * @param instance
	 *            id of the trigger being pause.
	 * @return
	 */
	@RequestMapping(value = "/{id:.+}/unschedule", method = RequestMethod.POST)
	public ResponseEntity<Void> unschedule(@PathVariable String id) {
		HttpStatus status = null;

		try {
			triggerInfoService.unschedule(id);
			status = HttpStatus.ACCEPTED;
		} catch (SchedulerException e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<>(status);
	}

	/**
	 * Reschedule a currently running trigger.
	 * 
	 * @param details
	 *            of the new schedule.
	 * @return
	 */
	@RequestMapping(value = "/{id:.+}/reschedule", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Void> reschedule(@RequestBody ReScheduleDetail reScheduleDetail) {
		HttpStatus status = null;

		try {
			triggerInfoService.reschedule(reScheduleDetail);
			status = HttpStatus.ACCEPTED;
		} catch (SchedulerException e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<>(status);
	}

}

package com.sapient.jat.controllers;

import java.util.Collection;
import java.util.List;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.jat.domains.SchedulerInfo;
import com.sapient.jat.domains.TriggerInfo;
import com.sapient.jat.services.SchedulerInfoService;
import com.sapient.jat.services.TriggerInfoService;
import com.sapient.jat.ui.resources.SchedulerInfoResource;
import com.sapient.jat.ui.resources.TriggerInfoResource;
import com.sapient.jat.ui.resources.TriggerInfoResourceAssembler;

@RestController
@ExposesResourceFor(TriggerInfo.class)
@RequestMapping(value = "/triggers", produces = "application/json")
public class TriggerRestController {
	
	@Autowired
	private TriggerInfoService triggerInfoService;
	@Autowired
	private TriggerInfoResourceAssembler triggerInfoResourceAssembler;
	
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
	
	
}

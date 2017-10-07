package com.sapient.jat.controllers;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.jat.domains.SchedulerInfo;
import com.sapient.jat.services.SchedulerInfoService;
import com.sapient.jat.ui.resources.SchedulerInfoResource;
import com.sapient.jat.ui.resources.SchedulerInfoResourceAssembler;

@RestController
@ExposesResourceFor(SchedulerInfo.class)
@RequestMapping(value = "/scheduler", produces = "application/json")
public class SchedulerRestController {
	
	private static Logger logger = LoggerFactory.getLogger(SchedulerRestController.class);
	
	@Autowired
	private SchedulerInfoService schedulerInfoService;
	@Autowired
	private SchedulerInfoResourceAssembler schedulerInfoResourceAssembler;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<SchedulerInfoResource> retrieveScheduler() {
		
		SchedulerInfo schedulerInfo = null;
		try {
			schedulerInfo = schedulerInfoService.getSchedulerInfo();
		} catch (SchedulerException e) {
			// TODO need to figure out exception handling
		}
		
		return new ResponseEntity<>(schedulerInfoResourceAssembler.toResource(schedulerInfo), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/standby", method = RequestMethod.POST)
	public ResponseEntity<Void> standby(@PathVariable String id) {
		
		HttpStatus status;
		
		try {
			schedulerInfoService.standby();
			status = HttpStatus.ACCEPTED;
		} catch (SchedulerException e) {
			//TODO figure out exception handling for scheduler
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<>(status);
	}
	
	@RequestMapping(value = "/{id}/start", method = RequestMethod.POST)
	public ResponseEntity<Void> start(@PathVariable String id) {
		HttpStatus status;
		
		try {
			schedulerInfoService.start();
			status = HttpStatus.ACCEPTED;
		} catch (SchedulerException e) {
			//TODO figure out exception handling for scheduler
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<>(status);
	}
	
	@RequestMapping(value = "/{id}/shutdown", method = RequestMethod.POST)
	public ResponseEntity<Void> shutdown(@PathVariable String id) {
		HttpStatus status;
		
		try {
			schedulerInfoService.shutdown();
			status = HttpStatus.ACCEPTED;
		} catch (SchedulerException e) {
			//TODO figure out exception handling for scheduler
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<>(status);
	}
	
	@RequestMapping(value = "/{id}/pause", method = RequestMethod.POST)
	public ResponseEntity<Void> pause(@PathVariable String id) {
		HttpStatus status;
		
		try {
			schedulerInfoService.pause();
			status = HttpStatus.ACCEPTED;
		} catch (SchedulerException e) {
			//TODO figure out exception handling for scheduler
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<>(status);
	}
	@RequestMapping(value = "/{id}/unpause", method = RequestMethod.POST)
	public ResponseEntity<Void> unpause(@PathVariable String id) {
		HttpStatus status;
		
		try {
			schedulerInfoService.unpause();
			status = HttpStatus.ACCEPTED;
		} catch (SchedulerException e) {
			//TODO figure out exception handling for scheduler
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<>(status);
	}
}
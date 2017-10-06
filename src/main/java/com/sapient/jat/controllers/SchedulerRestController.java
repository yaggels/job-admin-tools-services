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
	
	@RequestMapping(value = "/{id}/pause", method = RequestMethod.POST)
	public ResponseEntity<Void> pause(@PathVariable String id) {
		
		logger.info("In Method to Pause");
		
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
}

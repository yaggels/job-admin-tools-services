package com.sapient.jat.ui.resources;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import com.sapient.jat.controllers.SchedulerRestController;
import com.sapient.jat.domains.SchedulerInfo;

@Component
public class SchedulerInfoResourceAssembler extends ResourceAssemblerBaseClass<SchedulerInfo, SchedulerInfoResource> {
	
	@Autowired
	protected EntityLinks entityLinks;
	
	@Override
	public SchedulerInfoResource toResource(SchedulerInfo domainObject) {
		SchedulerInfoResource resource = null;
		
		try {
			resource = new SchedulerInfoResource(domainObject);
		} catch (SchedulerException e) {
			//TODO how to handle scheduler exception
		}
		
		final Link selfLink = entityLinks.linkToSingleResource(domainObject);
        resource.add(selfLink.withSelfRel());
        resource.add(linkTo(methodOn(SchedulerRestController.class).standby(resource.getName())).withRel("standby"));
        resource.add(linkTo(methodOn(SchedulerRestController.class).start(resource.getName())).withRel("start"));
        resource.add(linkTo(methodOn(SchedulerRestController.class).shutdown(resource.getName())).withRel("shutdown"));
        resource.add(linkTo(methodOn(SchedulerRestController.class).pause(resource.getName())).withRel("pause"));
        resource.add(linkTo(methodOn(SchedulerRestController.class).unpause(resource.getName())).withRel("unpause"));
        //resource.add(selfLink.withRel(DELETE_REL));
        return resource;
	}

}

package com.sapient.jat.ui.resources;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import com.sapient.jat.controllers.TriggerRestController;
import com.sapient.jat.domains.TriggerInfo;

@Component
public class TriggerInfoResourceAssembler extends ResourceAssemblerBaseClass<TriggerInfo, TriggerInfoResource> {

	@Autowired
	protected EntityLinks entityLinks;

	@Override
	public TriggerInfoResource toResource(TriggerInfo domainObject) {

		TriggerInfoResource resource = new TriggerInfoResource(domainObject);

		final Link selfLink = entityLinks.linkToSingleResource(domainObject);
		resource.add(selfLink.withSelfRel());
		resource.add(linkTo(methodOn(TriggerRestController.class).pause(resource.getTriggerId())).withRel("pause"));
		resource.add(linkTo(methodOn(TriggerRestController.class).resume(resource.getTriggerId())).withRel("resume"));
		resource.add(linkTo(methodOn(TriggerRestController.class).runNow(resource.getTriggerId())).withRel("runnow"));
		resource.add(linkTo(methodOn(TriggerRestController.class).unschedule(resource.getTriggerId())).withRel("unschedule"));
		//resource.add(linkTo(methodOn(TriggerRestController.class).reschedule(resource.getTriggerId())).withRel("reschedule"));

		return resource;
	}
}

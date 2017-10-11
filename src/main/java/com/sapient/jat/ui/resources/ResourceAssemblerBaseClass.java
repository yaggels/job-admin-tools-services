package com.sapient.jat.ui.resources;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * 
 * Abstract base class to assemble a resource exposing it as a domain objects to
 * the UI.
 * 
 * @author Johnson Chow
 *
 * @param <DomainType>
 * @param <ResourceType>
 */
public abstract class ResourceAssemblerBaseClass<DomainType, ResourceType> {

	public abstract ResourceType toResource(DomainType domainObject);

	public Collection<ResourceType> toResourceCollection(Collection<DomainType> domainObjects) {
		return domainObjects.stream().map(o -> toResource(o)).collect(Collectors.toList());
	}

}

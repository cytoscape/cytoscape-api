package org.cytoscape.property;

import org.cytoscape.event.AbstractCyEvent;

/**
 * This event signals that a CyProperty is updated.
 * @CyAPI.InModule property-api
 */
// TODO: This really should be a final class but changing it now would break API
public class PropertyUpdatedEvent extends AbstractCyEvent<CyProperty<?>> {

	/**
	 * Constructs the event.
	 * @param source Updated CyProperty object. 
	 */
	public PropertyUpdatedEvent(CyProperty<?> source) {
		super(source, PropertyUpdatedListener.class);
	}
}

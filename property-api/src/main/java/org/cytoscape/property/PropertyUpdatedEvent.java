package org.cytoscape.property;

import org.cytoscape.event.AbstractCyEvent;

/**
 * This event signals that a CyProperty is updated.
 * @author rozagh
 *
 */
public class PropertyUpdatedEvent extends AbstractCyEvent<CyProperty<?>> {

	/**
	 * Constructs the event.
	 * @param source Updated CyProperty object. 
	 */
	public PropertyUpdatedEvent(CyProperty<?> source) {
		super(source, PropertyUpdatedListener.class);
	}
}

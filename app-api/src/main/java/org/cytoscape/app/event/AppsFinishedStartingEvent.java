package org.cytoscape.app.event;

import org.cytoscape.event.AbstractCyEvent;

/**
 * An event that occurs when the Cytoscape core has started, and any
 * installed apps have either finished starting or failed to start.
 * 
 * @CyAPI.Final.Class
 * @CyAPI.InModule app-api
 */
public final class AppsFinishedStartingEvent extends AbstractCyEvent<Object> {
	
	/**
	 * Create a new event with the given source.
	 * 
	 * @param source the object that fired this event.
	 */
	public AppsFinishedStartingEvent(Object source) {
		super(source, AppsFinishedStartingListener.class);
	}
}

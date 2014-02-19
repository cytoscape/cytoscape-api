package org.cytoscape.app.event;

import org.cytoscape.event.AbstractCyEvent;

/**
 * An event that occurs when apps that are installed prior to launch have all
 * successfully finished starting up.  This event will not be fired if at
 * least one app does not start properly.
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

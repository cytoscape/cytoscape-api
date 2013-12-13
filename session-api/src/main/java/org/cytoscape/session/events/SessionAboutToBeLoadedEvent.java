package org.cytoscape.session.events;

import org.cytoscape.event.AbstractCyEvent;

/**
 * This event is fired right before Cytoscape begins to load a session.
 * @CyAPI.Final.Class
 * @CyAPI.InModule session-api
 */
public final class SessionAboutToBeLoadedEvent extends AbstractCyEvent<Object> {

	/**
	 * Constructor.
	 * @param source The object that fired this event.
	 */
	public SessionAboutToBeLoadedEvent(final Object source) {
		super(source, SessionAboutToBeLoadedListener.class);
	}
}

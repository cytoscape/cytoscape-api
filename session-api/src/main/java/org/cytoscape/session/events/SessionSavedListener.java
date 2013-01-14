package org.cytoscape.session.events;

import org.cytoscape.event.CyListener;

/**
 * A listener for {@link SessionSavedEvent}s.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule session-api
 */
public interface SessionSavedListener extends CyListener {
	
	/**
	 * Processes the specified event when fired.
	 * @param e The event that the listener is listening for.
	 */
	public void handleEvent(final SessionSavedEvent e);
}
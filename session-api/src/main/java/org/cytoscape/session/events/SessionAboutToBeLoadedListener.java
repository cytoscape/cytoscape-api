
package org.cytoscape.session.events;

import org.cytoscape.event.CyListener;

/**
 * Any object that needs to know that a {@link org.cytoscape.session.CySession} is about to be
 * loaded or created should implement this listener.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule session-api
 */
public interface SessionAboutToBeLoadedListener extends CyListener {
	
	/**
	 * Processes the {@link SessionAboutToBeLoadedEvent} when it is fired.
	 * @param e The fired event.
	 */
	public void handleEvent(SessionAboutToBeLoadedEvent e);
}

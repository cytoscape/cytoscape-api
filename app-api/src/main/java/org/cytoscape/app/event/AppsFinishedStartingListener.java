package org.cytoscape.app.event;

import org.cytoscape.event.CyListener;

/**
 * Listener for the {@link AppsFinishedStartingEvent}.
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule app-api
 */
public interface AppsFinishedStartingListener extends CyListener {
	
	/**
	 * Handles the given event.
	 * @param event the event to be handled.
	 */
	void handleEvent(AppsFinishedStartingEvent event);
}

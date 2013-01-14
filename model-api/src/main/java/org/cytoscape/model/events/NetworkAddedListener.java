package org.cytoscape.model.events;

import org.cytoscape.event.CyListener;


/**
 * Listener for {@link NetworkAddedEvent}
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule model-api
 */
public interface NetworkAddedListener extends CyListener {
	/**
	 * The method that should handle the specified event.
	 * @param e The event to be handled.
	 */
	public void handleEvent(NetworkAddedEvent e);
}

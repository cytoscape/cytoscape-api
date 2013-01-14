package org.cytoscape.model.events;

import org.cytoscape.event.CyListener;

/**
 * Listener for {@link RowsCreatedEvent}
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule model-api
 */
public interface RowsCreatedListener extends CyListener {
	/**
	 * The method that should handle the specified event.
	 * @param e The event to be handled.
	 */
	void handleEvent(RowsCreatedEvent e);
}

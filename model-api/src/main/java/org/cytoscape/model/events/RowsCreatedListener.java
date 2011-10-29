package org.cytoscape.model.events;

import org.cytoscape.event.CyListener;

/**
 * Listener for {@link RowsCreatedEvent}
 * @CyAPI.Spi.Interface
 */
public interface RowsCreatedListener extends CyListener {
	/**
	 * The method that should handle the specified event.
	 * @param e The event to be handled.
	 */
	void handleEvent(RowsCreatedEvent e);
}

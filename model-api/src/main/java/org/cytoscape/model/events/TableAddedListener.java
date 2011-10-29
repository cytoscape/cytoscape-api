package org.cytoscape.model.events;

import org.cytoscape.event.CyListener;

/**
 * Listener for {@link TableAddedEvent}.
 * @CyAPI.Spi.Interface
 */
public interface TableAddedListener extends CyListener {
	/**
	 * The method that should handle the specified event.
	 * @param e The event to be handled.
	 */
	public void handleEvent(final TableAddedEvent e);

}

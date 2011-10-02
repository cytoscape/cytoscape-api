package org.cytoscape.model.events;


import org.cytoscape.event.CyListener;


/**
 * Listener for {@link TableDeletedEvent}
 *
 */
public interface TableDeletedListener extends CyListener {
	/**
	 * The method that should handle the specified event.
	 * @param e The event to be handled.
	 */
	public void handleEvent(TableDeletedEvent e);
}

package org.cytoscape.group.events;

import org.cytoscape.event.CyListener;


/**
 * Listener for {@link GroupAddedEvent}
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule group-api
 */
public interface GroupAddedListener extends CyListener {
	/**
	 * The method that should handle the specified event.
	 * @param e The event to be handled.
	 */
	public void handleEvent(GroupAddedEvent e);
}

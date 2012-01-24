package org.cytoscape.group.events;

import org.cytoscape.event.CyListener;


/**
 * Listener for {@link GroupChangedEvent}
 * @CyAPI.Spi.Interface
 */
public interface GroupChangedListener extends CyListener {
	/**
	 * The method that should handle the specified event.
	 * @param e The event to be handled.
	 */
	public void handleEvent(GroupChangedEvent e);
}

package org.cytoscape.group.events;

import org.cytoscape.event.CyListener;


/**
 * Listener for {@link GroupAboutToCollapseEvent}
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule group-api
 */
public interface GroupAboutToCollapseListener extends CyListener {
	/**
	 * The method that should handle the specified event.
	 * @param e The event to be handled.
	 */
	public void handleEvent(GroupAboutToCollapseEvent e);
}

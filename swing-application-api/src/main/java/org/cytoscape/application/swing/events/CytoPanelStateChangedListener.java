
package org.cytoscape.application.swing.events;

import org.cytoscape.event.CyListener;

/**
 * A listener for handling CytoPanelStateChangedEvents.
 */
public interface CytoPanelStateChangedListener extends CyListener {

	/**
	 * Handles specified event.
	 * @param e The event to be handled.
	 */
	public void handleEvent(CytoPanelStateChangedEvent e);
}

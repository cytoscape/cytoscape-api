
package org.cytoscape.application.swing.events;

import org.cytoscape.event.CyListener;

/**
 * A listener for handling {@link CytoPanelStateChangedEvent}s.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule swing-application-api
 */
public interface CytoPanelStateChangedListener extends CyListener {

	/**
	 * Handles specified event.
	 * @param e The event to be handled.
	 */
	void handleEvent(CytoPanelStateChangedEvent e);
}

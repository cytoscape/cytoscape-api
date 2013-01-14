
package org.cytoscape.application.swing.events;

import org.cytoscape.event.CyListener;

/**
 * The listener for handling {@link CytoPanelComponentSelectedEvent}.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule swing-application-api
 */
public interface CytoPanelComponentSelectedListener extends CyListener {

	/**
	 * Handles specified event.
	 * @param e The event to be handled.
	 */
	void handleEvent(CytoPanelComponentSelectedEvent e);
}

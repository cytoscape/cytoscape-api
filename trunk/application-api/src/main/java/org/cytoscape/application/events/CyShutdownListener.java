
package org.cytoscape.application.events;

import org.cytoscape.event.CyListener;

/**
 * The listener for the {@link CyShutdownEvent}.
 * @CyAPI.Spi.Interface
 */
public interface CyShutdownListener extends CyListener {

	/**
	 * Process the specified event.
	 * @param e The event being processed.
	 */
	public void handleEvent(CyShutdownEvent e);
}

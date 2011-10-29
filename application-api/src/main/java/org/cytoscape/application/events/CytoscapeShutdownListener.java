
package org.cytoscape.application.events;

import org.cytoscape.event.CyListener;

/**
 * The listener for the {@link CytoscapeShutdownEvent}.
 * @CyAPI.Spi.Interface
 */
public interface CytoscapeShutdownListener extends CyListener {

	/**
	 * Process the specified event.
	 * @param e The event being processed.
	 */
	public void handleEvent(CytoscapeShutdownEvent e);
}

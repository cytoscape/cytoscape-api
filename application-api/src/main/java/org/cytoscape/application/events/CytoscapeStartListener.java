
package org.cytoscape.application.events;

import org.cytoscape.event.CyListener;

/**
 * The listener for the {@link CytoscapeStartEvent}.
 * @CyAPI.Spi.Interface
 */
public interface CytoscapeStartListener extends CyListener {

	/**
	 * Process the specified event.
	 * @param e The event being processed.
	 */
	public void handleEvent(CytoscapeStartEvent e);
}

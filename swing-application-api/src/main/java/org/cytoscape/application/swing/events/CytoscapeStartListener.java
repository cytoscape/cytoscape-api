
package org.cytoscape.application.swing.events;

import org.cytoscape.event.CyListener;

/**
 * The listener for the CytoscapeStartEvent.
 */
public interface CytoscapeStartListener extends CyListener {

	/**
	 * Process the specified event.
	 * @param e The event being processed.
	 */
	public void handleEvent(CytoscapeStartEvent e);
}


package org.cytoscape.application.events;

import org.cytoscape.event.CyListener;

/**
 * The listener for the {@link CyStartEvent}.
 * @CyAPI.Spi.Interface
 */
public interface CyStartListener extends CyListener {

	/**
	 * Process the specified event.
	 * @param e The event being processed.
	 */
	public void handleEvent(CyStartEvent e);
}

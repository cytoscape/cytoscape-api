
package org.cytoscape.application.events;

import org.cytoscape.event.CyListener;

/**
 * A listener for {@link SetCurrentNetworkViewEvent}s.
 * @CyAPI.Spi.Interface
 */
public interface SetCurrentNetworkViewListener extends CyListener {
	/**
	 * Processes the specified event when fired.
	 * @param e The event that the listener is listening for.
	 */
	public void handleEvent(SetCurrentNetworkViewEvent e);
}

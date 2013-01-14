
package org.cytoscape.application.events;

import org.cytoscape.event.CyListener;

/**
 * A listener for {@link SetSelectedNetworkViewsEvent}s. 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule application-api
 */
public interface SetSelectedNetworkViewsListener extends CyListener {
	/**
	 * Processes the specified event when fired.
	 * @param e The event that the listener is listening for.
	 */
	public void handleEvent(SetSelectedNetworkViewsEvent e);
}

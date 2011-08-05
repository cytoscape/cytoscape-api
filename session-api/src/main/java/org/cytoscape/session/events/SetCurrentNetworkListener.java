
package org.cytoscape.session.events;

import org.cytoscape.event.CyListener;

/**
 * A listener for {@link SetCurrentNetworkEvent}s.
 */
public interface SetCurrentNetworkListener extends CyListener {
	/**
	 * Processes the specified event when fired.
	 * @param e The event that the listener is listening for.
	 */
	public void handleEvent(SetCurrentNetworkEvent e);
}

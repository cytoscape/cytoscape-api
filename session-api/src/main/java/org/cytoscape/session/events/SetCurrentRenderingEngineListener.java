package org.cytoscape.session.events;

import org.cytoscape.event.CyListener;

public interface SetCurrentRenderingEngineListener extends CyListener {
	/**
	 * Processes the specified event when fired.
	 * 
	 * @param e
	 *            The event that the listener is listening for.
	 */
	public void handleEvent(final SetCurrentRenderingEngineEvent e);
}

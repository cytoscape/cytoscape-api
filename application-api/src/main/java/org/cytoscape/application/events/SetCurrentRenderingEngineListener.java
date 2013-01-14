package org.cytoscape.application.events;

import org.cytoscape.event.CyListener;

/**
 * Listener for {@link SetCurrentRenderingEngineEvent}.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule application-api
 */
public interface SetCurrentRenderingEngineListener extends CyListener {
	/**
	 * Processes the specified event when fired.
	 * 
	 * @param e The event that the listener is listening for.
	 */
	public void handleEvent(final SetCurrentRenderingEngineEvent e);
}

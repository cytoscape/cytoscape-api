package org.cytoscape.model.events;


import org.cytoscape.event.CyListener;

/**
 * Listener for {@link NetworkAboutToBeDestroyedEvent}
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule model-api
 */
public interface NetworkAboutToBeDestroyedListener extends CyListener {
	/**
	 * The method that should handle the specified event.
	 * @param e The event to be handled.
	 */
	public void handleEvent(NetworkAboutToBeDestroyedEvent e);
}

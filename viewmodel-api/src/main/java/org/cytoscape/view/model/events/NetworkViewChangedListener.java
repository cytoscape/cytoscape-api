package org.cytoscape.view.model.events;

import org.cytoscape.event.CyListener;

/**
 * Listener for {@linkplain NetworkViewChangedEvent}.
 * @CyAPI.Spi.Interface
 */
public interface NetworkViewChangedListener extends CyListener {
	
	/**
	 * Process event.
	 * 
	 * @param e the {@link NetworkViewChangedEvent} to be handled.
	 */
	void handleEvent(NetworkViewChangedEvent e);
}

package org.cytoscape.view.model.events;


import org.cytoscape.event.CyListener;


/**
 * Listener for {@linkplain NetworkViewDestroyedEvent}.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule viewmodel-api
 */
public interface NetworkViewDestroyedListener extends CyListener {
	
	/**
	 * Process event.
	 * 
	 * @param e the {@link NetworkViewDestroyedEvent} to be handled.
	 */
	public void handleEvent(NetworkViewDestroyedEvent e);
}

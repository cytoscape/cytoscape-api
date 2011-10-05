package org.cytoscape.view.model.events;


import org.cytoscape.event.CyListener;


/**
 * Listener for {@linkplain NetworkViewAddedEvent}.
 *
 */
public interface NetworkViewAddedListener extends CyListener {
	
	/**
	 * Process event
	 * 
	 * @param e the {@link NetworkViewAddedEvent} to be handled.
	 */
	public void handleEvent(NetworkViewAddedEvent e);
}

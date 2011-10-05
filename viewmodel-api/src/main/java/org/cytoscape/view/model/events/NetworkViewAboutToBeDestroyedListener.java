package org.cytoscape.view.model.events;


import org.cytoscape.event.CyListener;


/**
 * Listener for {@linkplain NetworkViewAboutToBeDestroyedEvent}.
 *
 */
public interface NetworkViewAboutToBeDestroyedListener extends CyListener {
	
	/**
	 * Process event
	 * 
	 * @param e the {@link NetworkViewAboutToBeDestroyedEvent} to be handled.
	 */
	public void handleEvent(NetworkViewAboutToBeDestroyedEvent e);
}

package org.cytoscape.view.model.events;


import org.cytoscape.event.CyListener;


/**
 * 
 */
public interface NetworkViewAddedListener extends CyListener {
	public void handleEvent(NetworkViewAddedEvent e);
}

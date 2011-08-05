package org.cytoscape.model.events;


import org.cytoscape.event.CyListener;

/**
 * 
 */
public interface NetworkAboutToBeDestroyedListener extends CyListener {
	public void handleEvent(NetworkAboutToBeDestroyedEvent e);
}

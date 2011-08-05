package org.cytoscape.view.model.events;


import org.cytoscape.event.CyListener;


/**
 * 
 */
public interface NetworkViewAboutToBeDestroyedListener extends CyListener {
	public void handleEvent(NetworkViewAboutToBeDestroyedEvent e);
}

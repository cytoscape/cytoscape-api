package org.cytoscape.model.events;

import org.cytoscape.event.CyListener;


/**
 * 
 */
public interface NetworkAddedListener extends CyListener {
	public void handleEvent(NetworkAddedEvent e);
}

package org.cytoscape.model.events;

import org.cytoscape.event.CyListener;

/**
 * Listener for {@link TableAddedEvent}.
 * 
 */
public interface TableAddedListener extends CyListener {
	
	public void handleEvent(final TableAddedEvent e);

}

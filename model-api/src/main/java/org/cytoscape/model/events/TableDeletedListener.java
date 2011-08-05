package org.cytoscape.model.events;


import org.cytoscape.event.CyListener;


public interface TableDeletedListener extends CyListener {
	public void handleEvent(TableDeletedEvent e);
}

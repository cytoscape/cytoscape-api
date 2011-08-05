package org.cytoscape.model.events;


import org.cytoscape.event.CyListener;


public interface TableAboutToBeDeletedListener extends CyListener {
	public void handleEvent(TableAboutToBeDeletedEvent e);
}

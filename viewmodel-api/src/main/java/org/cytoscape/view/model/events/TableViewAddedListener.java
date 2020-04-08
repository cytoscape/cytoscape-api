package org.cytoscape.view.model.events;

import org.cytoscape.event.CyListener;

public interface TableViewAddedListener extends CyListener {

	public void handleEvent(TableViewAddedEvent e);
	
}

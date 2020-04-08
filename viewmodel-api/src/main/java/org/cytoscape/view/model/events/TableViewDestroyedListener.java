package org.cytoscape.view.model.events;

import org.cytoscape.event.CyListener;

public interface TableViewDestroyedListener extends CyListener {
	
	public void handleEvent(TableViewDestroyedEvent e);

}

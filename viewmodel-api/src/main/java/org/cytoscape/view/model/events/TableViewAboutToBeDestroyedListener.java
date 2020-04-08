package org.cytoscape.view.model.events;

import org.cytoscape.event.CyListener;

public interface TableViewAboutToBeDestroyedListener extends CyListener {
	
	public void handleEvent(TableViewAboutToBeDestroyedEvent e);

}

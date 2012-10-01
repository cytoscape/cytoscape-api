package org.cytoscape.model.events;

import org.cytoscape.event.CyListener;

public interface TableTitleChangedListener extends CyListener{
	
	void handleEvent (TableTitleChangedEvent e);

}

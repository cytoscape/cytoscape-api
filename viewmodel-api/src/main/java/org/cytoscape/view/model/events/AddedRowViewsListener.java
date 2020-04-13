package org.cytoscape.view.model.events;

import org.cytoscape.event.CyListener;

public interface AddedRowViewsListener extends CyListener {

	void handleEvent(AddedRowViewsEvent e);
	
}

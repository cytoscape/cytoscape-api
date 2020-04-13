package org.cytoscape.view.model.events;

import org.cytoscape.event.CyListener;

public interface AddedColumnViewListener extends CyListener {

	void handleEvent(AddedColumnViewEvent e);
	
}

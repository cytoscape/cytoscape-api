package org.cytoscape.view.model.events;

import org.cytoscape.event.CyListener;

public interface AboutToRemoveColumnViewListener extends CyListener {

	void handleEvent(AboutToRemoveColumnViewEvent e);
	
}

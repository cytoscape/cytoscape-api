package org.cytoscape.view.model.events;

import org.cytoscape.event.CyListener;

public interface AboutToRemoveNodeViewsListener extends CyListener{
	void handleEvent(AboutToRemoveNodeViewsEvent e);
}

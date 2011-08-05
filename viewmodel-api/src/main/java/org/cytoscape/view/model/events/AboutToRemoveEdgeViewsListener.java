package org.cytoscape.view.model.events;

import org.cytoscape.event.CyListener;

public interface AboutToRemoveEdgeViewsListener extends CyListener {

	void handleEvent(AboutToRemoveEdgeViewsEvent e);
}

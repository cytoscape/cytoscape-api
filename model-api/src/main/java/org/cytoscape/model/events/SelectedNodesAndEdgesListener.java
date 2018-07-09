package org.cytoscape.model.events;

import org.cytoscape.event.CyListener;


public interface SelectedNodesAndEdgesListener extends CyListener {

	void handleEvent(SelectedNodesAndEdgesEvent event);
}

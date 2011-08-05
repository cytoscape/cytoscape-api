package org.cytoscape.view.model.events;

import org.cytoscape.event.CyListener;

public interface NodeViewsChangedListener extends CyListener {
	void handleEvent(NodeViewsChangedEvent e);
}

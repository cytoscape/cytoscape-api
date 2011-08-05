package org.cytoscape.view.model.events;

import org.cytoscape.event.CyListener;

public interface EdgeViewsChangedListener extends CyListener {
	void handleEvent(EdgeViewsChangedEvent e);
}

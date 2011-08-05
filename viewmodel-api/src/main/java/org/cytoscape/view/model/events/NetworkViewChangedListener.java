package org.cytoscape.view.model.events;

import org.cytoscape.event.CyListener;

public interface NetworkViewChangedListener extends CyListener {
	void handleEvent(NetworkViewChangedEvent e);
}

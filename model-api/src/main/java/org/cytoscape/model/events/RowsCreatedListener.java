package org.cytoscape.model.events;

import org.cytoscape.event.CyListener;

public interface RowsCreatedListener extends CyListener {

	void handleEvent(RowsCreatedEvent e);
}

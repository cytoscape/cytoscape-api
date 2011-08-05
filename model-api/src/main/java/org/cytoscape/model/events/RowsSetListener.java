package org.cytoscape.model.events;

import org.cytoscape.event.CyListener;

public interface RowsSetListener extends CyListener {

	void handleEvent(RowsSetEvent e);
}

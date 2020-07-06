package org.cytoscape.application.events;

import org.cytoscape.event.CyListener;

public interface SetCurrentTableListener extends CyListener {

	public void handleEvent(SetCurrentTableEvent e);
}

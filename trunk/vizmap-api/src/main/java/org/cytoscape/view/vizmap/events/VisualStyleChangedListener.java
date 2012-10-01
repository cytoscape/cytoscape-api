package org.cytoscape.view.vizmap.events;

import org.cytoscape.event.CyListener;

public interface VisualStyleChangedListener extends CyListener {

	void handleEvent(final VisualStyleChangedEvent e);

}

package org.cytoscape.view.model.events;

import org.cytoscape.event.CyListener;

public interface AboutToRemoveRowViewsListener extends CyListener {

	void handleEvent(AboutToRemoveRowViewsEvent e);
}

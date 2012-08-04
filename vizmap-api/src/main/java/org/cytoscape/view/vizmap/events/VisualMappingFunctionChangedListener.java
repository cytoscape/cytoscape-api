package org.cytoscape.view.vizmap.events;

import org.cytoscape.event.CyListener;

/**
 * 
 *
 */
public interface VisualMappingFunctionChangedListener extends CyListener {
	
	void handleEvent(VisualMappingFunctionChangedEvent e);

}

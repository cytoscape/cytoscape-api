package org.cytoscape.model.events;

import org.cytoscape.event.CyListener;


/**
 * Listener for {@link SelectedNodesAndEdgesEvent}
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule model-api
 */
public interface SelectedNodesAndEdgesListener extends CyListener {

	void handleEvent(SelectedNodesAndEdgesEvent event);
}

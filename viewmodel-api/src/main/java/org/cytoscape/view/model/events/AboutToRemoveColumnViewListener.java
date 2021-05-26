package org.cytoscape.view.model.events;

import org.cytoscape.event.CyListener;


/**
 * Listener for {@link AboutToRemoveColumnViewEvent}.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule viewmodel-api
 * 
 * @since 3.9
 */
public interface AboutToRemoveColumnViewListener extends CyListener {

	void handleEvent(AboutToRemoveColumnViewEvent e);
	
}

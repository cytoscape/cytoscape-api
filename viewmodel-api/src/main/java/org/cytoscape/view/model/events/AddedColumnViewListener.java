package org.cytoscape.view.model.events;

import org.cytoscape.event.CyListener;

/**
 * Listener for {@link AddedColumnViewEvent}.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule viewmodel-api
 * @since 3.9
 */
public interface AddedColumnViewListener extends CyListener {

	void handleEvent(AddedColumnViewEvent e);
	
}

package org.cytoscape.view.model.events;

import org.cytoscape.event.CyListener;

/**
 * Listener for {@link AddedRowViewsEvent}.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule viewmodel-api
 * @since 3.9
 */
public interface AddedRowViewsListener extends CyListener {

	void handleEvent(AddedRowViewsEvent e);
	
}

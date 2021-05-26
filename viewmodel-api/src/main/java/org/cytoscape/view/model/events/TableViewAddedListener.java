package org.cytoscape.view.model.events;

import org.cytoscape.event.CyListener;


/**
 * Listener for {@linkplain TableViewAddedEvent}.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule viewmodel-api
 * @since 3.9
 */
public interface TableViewAddedListener extends CyListener {

	public void handleEvent(TableViewAddedEvent e);
	
}

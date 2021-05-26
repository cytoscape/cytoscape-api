package org.cytoscape.view.model.events;

import org.cytoscape.event.CyListener;


/**
 * Listener for {@linkplain TableViewDestroyedEvent}.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule viewmodel-api
 * @since 3.9
 */
public interface TableViewDestroyedListener extends CyListener {
	
	public void handleEvent(TableViewDestroyedEvent e);

}

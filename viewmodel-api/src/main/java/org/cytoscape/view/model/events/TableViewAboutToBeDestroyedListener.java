package org.cytoscape.view.model.events;

import org.cytoscape.event.CyListener;

/**
 * Listener for {@link TableViewAboutToBeDestroyedEvent}.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule viewmodel-api
 * @since 3.9
 */
public interface TableViewAboutToBeDestroyedListener extends CyListener {
	
	public void handleEvent(TableViewAboutToBeDestroyedEvent e);

}

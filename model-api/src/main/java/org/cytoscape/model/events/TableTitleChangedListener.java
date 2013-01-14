package org.cytoscape.model.events;

import org.cytoscape.event.CyListener;

/**
 * TODO: Missing documentation
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule model-api
 */
public interface TableTitleChangedListener extends CyListener{
	
	void handleEvent (TableTitleChangedEvent e);

}

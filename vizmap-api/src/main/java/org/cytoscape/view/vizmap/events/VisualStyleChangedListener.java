package org.cytoscape.view.vizmap.events;

import org.cytoscape.event.CyListener;

/**
 * TODO: Missing documentation
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule vizmap-api
 */
public interface VisualStyleChangedListener extends CyListener {

	void handleEvent(final VisualStyleChangedEvent e);

}

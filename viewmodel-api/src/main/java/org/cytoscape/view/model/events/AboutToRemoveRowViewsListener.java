package org.cytoscape.view.model.events;

import org.cytoscape.event.CyListener;

/**
 * Listener for {@link AboutToRemoveRowViewsEvent}.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule viewmodel-api
 * 
 * @since 3.9
 */
public interface AboutToRemoveRowViewsListener extends CyListener {

	void handleEvent(AboutToRemoveRowViewsEvent e);
}

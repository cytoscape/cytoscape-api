package org.cytoscape.view.model.events;

import org.cytoscape.event.CyListener;

/**
 * Listener for {@linkplain AboutToRemoveEdgeViewsEvent}.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule viewmodel-api
 */
public interface AboutToRemoveEdgeViewsListener extends CyListener {

	/**
	 * Process event.
	 * 
	 * @param e the {@link AboutToRemoveEdgeViewsEvent} to be handled.
	 */
	void handleEvent(AboutToRemoveEdgeViewsEvent e);
}

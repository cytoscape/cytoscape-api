package org.cytoscape.view.model.events;

import org.cytoscape.event.CyListener;

/**
 * Listener for {@linkplain AboutToRemoveNodeViewsEvent}.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule viewmodel-api
 */
public interface AboutToRemoveNodeViewsListener extends CyListener{
	
	/**
	 * Process event.
	 * 
	 * @param e the {@link AboutToRemoveNodeViewsEvent} to be handled.
	 */
	void handleEvent(AboutToRemoveNodeViewsEvent e);
}

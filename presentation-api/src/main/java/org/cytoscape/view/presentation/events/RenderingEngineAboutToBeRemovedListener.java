package org.cytoscape.view.presentation.events;

/**
 * Listener for {@link RenderingEngineAboutToBeRemovedEvent}.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule presentation-api
 */
public interface RenderingEngineAboutToBeRemovedListener {
	
	/**
	 * Perform pre-deletion tasks in this method.
	 * 
	 * @param evt event containing deleted presentation's RenderingEngine.
	 */
	void handleEvent(final RenderingEngineAboutToBeRemovedEvent evt);

}

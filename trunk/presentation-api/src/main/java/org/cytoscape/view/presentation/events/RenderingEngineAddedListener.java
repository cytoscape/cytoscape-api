package org.cytoscape.view.presentation.events;

import org.cytoscape.event.CyListener;

/**
 * Listener for {@linkplain RenderingEngineAddedEvent}.
 * {@linkplain org.cytoscape.view.presentation.RenderingEngineManager} implementation should implement this
 * interface, too.
 * @CyAPI.Spi.Interface
 */
public interface RenderingEngineAddedListener extends CyListener {

	/**
	 * Listener can extract source RenderingEngine object in this method. This
	 * is mainly for {@linkplain org.cytoscape.view.presentation.RenderingEngineManager}.
	 * 
	 * @param e an event object which contains source RenderingEngine.
	 */
	void handleEvent(final RenderingEngineAddedEvent e);
}

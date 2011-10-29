package org.cytoscape.view.model.events;

import org.cytoscape.event.CyListener;

/**
 * Listener for {@linkplain NodeViewsChangedEvent}.
 * @CyAPI.Spi.Interface
 */
public interface NodeViewsChangedListener extends CyListener {
	
	/**
	 * Process event.
	 * 
	 * @param e the {@link NodeViewsChangedEvent} to be handled.
	 */
	void handleEvent(NodeViewsChangedEvent e);
}

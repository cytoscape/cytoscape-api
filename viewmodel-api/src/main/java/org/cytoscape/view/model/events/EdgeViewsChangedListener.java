package org.cytoscape.view.model.events;

import org.cytoscape.event.CyListener;

/**
 * Listener for {@linkplain EdgeViewsChangedEvent}.
 *
 */
public interface EdgeViewsChangedListener extends CyListener {
	
	/**
	 * Process event
	 * 
	 * @param e the {@link EdgeViewsChangedEvent} to be handled.
	 */
	void handleEvent(EdgeViewsChangedEvent e);
}

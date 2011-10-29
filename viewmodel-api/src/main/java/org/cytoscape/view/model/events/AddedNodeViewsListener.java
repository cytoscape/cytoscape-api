
package org.cytoscape.view.model.events;

import org.cytoscape.event.CyListener;

/**
 * Listener for {@linkplain AddedNodeViewsEvent}.
 * @CyAPI.Spi.Interface
 */
public interface AddedNodeViewsListener extends CyListener {
	
	/**
	 * Process event
	 * 
	 * @param e the {@link AddedNodeViewsEvent} to be handled.
	 */
	public void handleEvent(AddedNodeViewsEvent e);
}

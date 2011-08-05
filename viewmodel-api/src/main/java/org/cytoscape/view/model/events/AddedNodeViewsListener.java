
package org.cytoscape.view.model.events;

import org.cytoscape.event.CyListener;

/**
 * Listener for {@linkplain AddedNodeViewsEvent}.
 *
 */
public interface AddedNodeViewsListener extends CyListener {
	
	/**
	 * Process event.
	 * 
	 * @param e
	 */
	public void handleEvent(AddedNodeViewsEvent e);
}

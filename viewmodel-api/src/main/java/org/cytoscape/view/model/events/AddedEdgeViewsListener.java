
package org.cytoscape.view.model.events;

import org.cytoscape.event.CyListener;

/**
 * Listener for {@linkplain AddedEdgeViewsEvent}.
 *
 */
public interface AddedEdgeViewsListener extends CyListener {
	
	/**
	 * Process event
	 * 
	 * @param e
	 */
	public void handleEvent(AddedEdgeViewsEvent e);

}


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
	 * @param e the {@link AddedEdgeViewsEvent} to be handled.
	 */
	public void handleEvent(AddedEdgeViewsEvent e);

}

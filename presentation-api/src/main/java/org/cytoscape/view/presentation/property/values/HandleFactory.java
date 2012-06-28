package org.cytoscape.view.presentation.property.values;

import org.cytoscape.model.CyEdge;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;


/**
 * Factory of the handle object.
 *
 */
public interface HandleFactory {
	
	/**
	 * Creates a new instance of the Handle.
	 * 
	 * @param x x position of the handle (absolute position)
	 * @param y y position of the handle (absolute position)
	 * 
	 * @return instance of new handle.
	 */
	Handle createHandle(final CyNetworkView graphView, final View<CyEdge> view, double x, double y);
}

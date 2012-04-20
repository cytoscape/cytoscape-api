package org.cytoscape.task;

import org.cytoscape.model.CyEdge;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;

/**
 * An EdgeViewTaskFactory that is always ready to produce a TaskIterator.
 */
public abstract class AbstractEdgeViewTaskFactory implements EdgeViewTaskFactory {
	/**
	 * By default always returns true.
	 * @param edgeView The edge view. 
	 * @param networkView The network view. 
	 * @return always returns true.
	 */
	@Override
	public boolean isReady(View<CyEdge> edgeView, CyNetworkView networkView) {
		return true;
	}
}

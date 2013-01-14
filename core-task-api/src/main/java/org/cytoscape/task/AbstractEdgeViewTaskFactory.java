package org.cytoscape.task;

import org.cytoscape.model.CyEdge;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;

/**
 * An EdgeViewTaskFactory that is always ready to produce a TaskIterator.
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule core-task-api
 */
public abstract class AbstractEdgeViewTaskFactory implements EdgeViewTaskFactory {
	/**
	 * Returns true if the supplied edge and network views are not null.
	 * @param edgeView The edge view. 
	 * @param networkView The network view. 
	 * @return true if the supplied edge and network views are not null.
	 */
	@Override
	public boolean isReady(View<CyEdge> edgeView, CyNetworkView networkView) {
		return edgeView != null && networkView != null;
	}
}

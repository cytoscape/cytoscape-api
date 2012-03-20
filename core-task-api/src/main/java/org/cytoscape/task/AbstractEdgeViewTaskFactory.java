package org.cytoscape.task;

import org.cytoscape.model.CyEdge;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;

/**
 * An EdgeViewTaskFactory that is always ready to produce a TaskIterator.
 */
public abstract class AbstractEdgeViewTaskFactory implements EdgeViewTaskFactory {
	@Override
	public boolean isReady(View<CyEdge> EdgeView, CyNetworkView networkView) {
		return true;
	}
}

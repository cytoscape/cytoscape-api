package org.cytoscape.task;

import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;

/**
 * A NodeViewTaskFactory that is always ready to produce a TaskIterator.
 * @CyAPI.Abstract.Class
 */
public abstract class AbstractNodeViewTaskFactory implements NodeViewTaskFactory {
	/**
	 * Always returns true.
	 * @param nodeView The node view
	 * @param networkView The network view
	 * @return always returns true.
	 */
	@Override
	public boolean isReady(View<CyNode> nodeView, CyNetworkView networkView) {
		return true;
	}
}

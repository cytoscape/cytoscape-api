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
	 * Returns true of the supplied network and node views are not null.
	 * @param nodeView The node view
	 * @param networkView The network view
	 * @return true of the supplied network and node views are not null.
	 */
	@Override
	public boolean isReady(View<CyNode> nodeView, CyNetworkView networkView) {
		return nodeView != null && networkView != null;
	}
}

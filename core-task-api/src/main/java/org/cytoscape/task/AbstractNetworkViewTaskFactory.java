package org.cytoscape.task;

import org.cytoscape.view.model.CyNetworkView;

/**
 * A NetworkViewTaskFactory that is always ready to produce a TaskIterator.
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule core-task-api
 */
public abstract class AbstractNetworkViewTaskFactory implements NetworkViewTaskFactory {
	/**
	 * Returns true if the supplied network view is not null.
	 * @param networkView The network view
	 * @return true if the supplied network view is not null.
	 */
	@Override
	public boolean isReady(CyNetworkView networkView) {
		return networkView != null;
	}
}

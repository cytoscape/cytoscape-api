package org.cytoscape.task;

import org.cytoscape.view.model.CyNetworkView;

/**
 * A NetworkViewTaskFactory that is always ready to produce a TaskIterator.
 * @CyAPI.Abstract.Class
 */
public abstract class AbstractNetworkViewTaskFactory implements NetworkViewTaskFactory {
	/**
	 * Always returns true.
	 * @param networkView The network view
	 * @return always returns true.
	 */
	@Override
	public boolean isReady(CyNetworkView networkView) {
		return true;
	}
}

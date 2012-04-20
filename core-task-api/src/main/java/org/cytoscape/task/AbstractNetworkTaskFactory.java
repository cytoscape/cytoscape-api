package org.cytoscape.task;

import org.cytoscape.model.CyNetwork;

/**
 * A NetworkTaskFactory that is always ready to produce a TaskIterator.
 * @CyAPI.Abstract.Class
 */
public abstract class AbstractNetworkTaskFactory implements NetworkTaskFactory {
	/**
	 * Always returns true.
	 * @param network The network.
	 * @return always returns true.
	 */
	@Override
	public boolean isReady(CyNetwork network) {
		return true;
	}
}

package org.cytoscape.task;

import org.cytoscape.model.CyNetwork;

/**
 * A NetworkTaskFactory that is always ready to produce a TaskIterator.
 * @CyAPI.Abstract.Class
 */
public abstract class AbstractNetworkTaskFactory implements NetworkTaskFactory {
	/**
	 * Returns true if the supplied network is not null.
	 * @param network The network.
	 * @return true if the supplied network is not null.
	 */
	@Override
	public boolean isReady(CyNetwork network) {
		return network != null;
	}
}

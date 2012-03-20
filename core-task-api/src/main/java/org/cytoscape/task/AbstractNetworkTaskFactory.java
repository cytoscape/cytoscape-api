package org.cytoscape.task;

import org.cytoscape.model.CyNetwork;

/**
 * A NetworkTaskFactory that is always ready to produce a TaskIterator.
 * @CyAPI.Abstract.Class
 */
public abstract class AbstractNetworkTaskFactory implements NetworkTaskFactory {
	@Override
	public boolean isReady(CyNetwork network) {
		return true;
	}
}

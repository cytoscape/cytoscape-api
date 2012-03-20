package org.cytoscape.task;

import java.util.Collection;

import org.cytoscape.model.CyNetwork;

/**
 * An NetworkCollectionTaskFactory that is always ready to produce a TaskIterator.
 */
public abstract class AbstractNetworkCollectionTaskFactory implements NetworkCollectionTaskFactory {
	@Override
	public boolean isReady(Collection<CyNetwork> networks) {
		return true;
	}
}

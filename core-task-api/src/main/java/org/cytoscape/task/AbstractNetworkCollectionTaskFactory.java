package org.cytoscape.task;

import java.util.Collection;

import org.cytoscape.model.CyNetwork;

/**
 * An NetworkCollectionTaskFactory that is always ready to produce a TaskIterator.
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule core-task-api
 */
public abstract class AbstractNetworkCollectionTaskFactory implements NetworkCollectionTaskFactory {
	/**
	 * Returns true if the supplied collection is not null.
	 * @param networks The collection of networks.
	 * @return true if the supplied collection is not null.
	 */
	@Override
	public boolean isReady(Collection<CyNetwork> networks) {
		return networks != null;
	}
}

package org.cytoscape.task;

import java.util.Collection;

import org.cytoscape.view.model.CyNetworkView;

/**
 * A NetworkViewCollectionTaskFactory that is always ready to produce a TaskIterator.
 * @CyAPI.Abstract.Class
 */
public abstract class AbstractNetworkViewCollectionTaskFactory implements NetworkViewCollectionTaskFactory {
	/**
	 * Returns true if the supplied collection is not null.
	 * @param networkViews The collection of network views.
	 * @return true if the supplied collection is not null.
	 */
	@Override
	public boolean isReady(Collection<CyNetworkView> networkViews) {
		return networkViews != null;
	}
}

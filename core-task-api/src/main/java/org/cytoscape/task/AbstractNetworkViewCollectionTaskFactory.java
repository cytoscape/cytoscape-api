package org.cytoscape.task;

import java.util.Collection;

import org.cytoscape.view.model.CyNetworkView;

/**
 * A NetworkViewCollectionTaskFactory that is always ready to produce a TaskIterator.
 * @CyAPI.Abstract.Class
 */
public abstract class AbstractNetworkViewCollectionTaskFactory implements NetworkViewCollectionTaskFactory {
	/**
	 * Always returns true.
	 * @param networkViews The collection of network views.
	 * @return always returns true.
	 */
	@Override
	public boolean isReady(Collection<CyNetworkView> networkViews) {
		return true;
	}
}

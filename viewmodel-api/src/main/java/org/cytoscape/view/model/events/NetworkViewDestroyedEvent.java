package org.cytoscape.view.model.events;


import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.event.AbstractCyEvent;


/**
 * 
 */
public final class NetworkViewDestroyedEvent extends AbstractCyEvent<CyNetworkViewManager> {
	public NetworkViewDestroyedEvent(final CyNetworkViewManager source) {
		super(source, NetworkViewDestroyedListener.class);
	}
}

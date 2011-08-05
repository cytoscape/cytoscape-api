package org.cytoscape.model.events;


import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.event.AbstractCyEvent;


/**
 * 
 */
public final class NetworkDestroyedEvent extends AbstractCyEvent<CyNetworkManager> {
	public NetworkDestroyedEvent(final CyNetworkManager source) {
		super(source, NetworkDestroyedListener.class);
	}
}

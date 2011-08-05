package org.cytoscape.view.model.events;


import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.view.model.CyNetworkView;


/**
 * 
 */
public final class NetworkViewAddedEvent extends AbstractNetworkViewEvent {
	public NetworkViewAddedEvent(final CyNetworkViewManager source, final CyNetworkView view) {
		super(source, NetworkViewAddedListener.class, view);
	}
}

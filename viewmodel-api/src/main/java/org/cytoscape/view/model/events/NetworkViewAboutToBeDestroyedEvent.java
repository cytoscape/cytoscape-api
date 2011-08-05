package org.cytoscape.view.model.events;


import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.view.model.CyNetworkView;


/**
 * 
 */
public final class NetworkViewAboutToBeDestroyedEvent extends AbstractNetworkViewEvent {
	public NetworkViewAboutToBeDestroyedEvent(final CyNetworkViewManager source, final CyNetworkView view) {
		super(source, NetworkViewAboutToBeDestroyedListener.class, view);
	}
}

package org.cytoscape.view.model.events;


import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.event.AbstractCyEvent;


/**
 * When a {@link CyNetworkView} is destroyed, this event will be fired.
 */
public final class NetworkViewDestroyedEvent extends AbstractCyEvent<CyNetworkViewManager> {
	/**
	 * Creates the event for network views that are destroyed.
	 * 
	 * @param source the {@link CyNetworkViewManager} of the CyNetworkView that was destroyed.
	 */
	public NetworkViewDestroyedEvent(final CyNetworkViewManager source) {
		super(source, NetworkViewDestroyedListener.class);
	}
}

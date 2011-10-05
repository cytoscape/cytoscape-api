package org.cytoscape.view.model.events;


import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;


/**
 * When a {@link CyNetworkView} is added to a {@linkplain CyNetworkViewManager}, this event will be fired.
 */
public final class NetworkViewAddedEvent extends AbstractNetworkViewEvent {
	/**
	 * Creates the event for network views that are added.
	 * 
	 * @param source the {@link CyNetworkViewManager} that the CyNetworkView was added to.
	 * @param view the {@link CyNetworkView} that was added.
	 */
	public NetworkViewAddedEvent(final CyNetworkViewManager source, final CyNetworkView view) {
		super(source, NetworkViewAddedListener.class, view);
	}
}

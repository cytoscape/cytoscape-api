package org.cytoscape.view.model.events;


import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;


/**
 * When a {@link CyNetworkView} is about to be destroyed, this event will be fired.
 * @CyAPI.Final.Class
 * @CyAPI.InModule viewmodel-api
 */
public final class NetworkViewAboutToBeDestroyedEvent extends AbstractNetworkViewEvent {
	/**
	 * Creates the event for network views that are about to be destroyed.
	 * 
	 * @param source the {@link CyNetworkViewManager} of the CyNetworkView about to be destroyed.
	 * @param view the {@link CyNetworkView} about to be destroyed.
	 */
	public NetworkViewAboutToBeDestroyedEvent(final CyNetworkViewManager source, final CyNetworkView view) {
		super(source, NetworkViewAboutToBeDestroyedListener.class, view);
	}
}

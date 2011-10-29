package org.cytoscape.model.events;


import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.model.CyNetwork;


/**
 * This event signals that a network is about to be destroyed.
 * @CyAPI.Final.Class
 */
public final class  NetworkAboutToBeDestroyedEvent extends AbstractNetworkEvent {
	/**
	 * Constructs event.
	 * @param source the {@link CyNetworkManager} of the network about to be destroyed.
	 * @param net the {@link CyNetwork} about to be destroyed.
	 */
	public NetworkAboutToBeDestroyedEvent(final CyNetworkManager source, final CyNetwork net) {
		super(source, NetworkAboutToBeDestroyedListener.class, net);
	}
}

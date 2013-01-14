package org.cytoscape.model.events;


import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.model.CyNetwork;


/**
 * This event signals that a network has been added.
 * @CyAPI.Final.Class
 * @CyAPI.InModule model-api
 */
public final class NetworkAddedEvent extends AbstractNetworkEvent {
	/**
	 * Constructs event.
	 * @param source the {@link CyNetworkManager} the network has been added to.
	 * @param net the {@link CyNetwork} that has been added.
	 */
	public NetworkAddedEvent(final CyNetworkManager source, final CyNetwork net) {
		super(source, NetworkAddedListener.class, net);
	}
}

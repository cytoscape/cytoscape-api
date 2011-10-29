package org.cytoscape.model.events;


import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.event.AbstractCyEvent;


/**
 * This event signals that a Network has been destroyed.
 * @CyAPI.Final.Class
 */
public final class NetworkDestroyedEvent extends AbstractCyEvent<CyNetworkManager> {
	/**
	 * Constructs event.
	 * @param source the {@link CyNetworkManager} from which the {@link org.cytoscape.model.CyNetwork} was destroyed.
	 */
	public NetworkDestroyedEvent(final CyNetworkManager source) {
		super(source, NetworkDestroyedListener.class);
	}
}

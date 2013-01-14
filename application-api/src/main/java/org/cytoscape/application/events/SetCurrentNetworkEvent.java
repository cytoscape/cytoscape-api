package org.cytoscape.application.events;


import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.model.CyNetwork;


/**
 * An event signaling that the a network has been set to current.
 * @CyAPI.Final.Class
 * @CyAPI.InModule application-api
 */
public final class SetCurrentNetworkEvent extends AbstractCyEvent<CyApplicationManager> {


	private final CyNetwork net;

	/**
	 * Returns the network associated with this event. The network returned may be null!
	 * @return the network associated with this event.
	 */
	public final CyNetwork getNetwork() {
		return net;
	}

	/**
	 * Constructor.
	 * @param source The application manager that is the source of the event.
	 * @param net The network that has been set to the current network.
	 */
	public SetCurrentNetworkEvent(final CyApplicationManager source, final CyNetwork net) {
		super(source, SetCurrentNetworkListener.class);
		this.net = net;
	}
}

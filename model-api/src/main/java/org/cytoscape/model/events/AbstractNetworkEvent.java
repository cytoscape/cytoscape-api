package org.cytoscape.model.events;


import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNetworkManager;


/**
 *  Base class for all derived concrete event classes classes in this package that require a CyNetwork.
 */
class AbstractNetworkEvent extends AbstractCyEvent<CyNetworkManager> {
	private final CyNetwork net;

	AbstractNetworkEvent(final CyNetworkManager source, final Class<?> listenerClass, final CyNetwork net) {
		super(source, listenerClass);

		if (net == null)
			throw new NullPointerException("the \"net\" parameter must never be null!");
		this.net = net;
	}

	public final CyNetwork getNetwork() {
		return net;
	}
}

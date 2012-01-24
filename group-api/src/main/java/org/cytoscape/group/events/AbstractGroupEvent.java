package org.cytoscape.group.events;


import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.group.CyGroup;
import org.cytoscape.group.CyGroupManager;
import org.cytoscape.model.CyNetwork;


/**
 *  Base class for all derived concrete event classes classes in this package that require a CyNetwork.
 */
class AbstractGroupEvent extends AbstractCyEvent<CyGroup> {
	private final CyNetwork net;

	AbstractGroupEvent(final CyGroup source, final Class<?> listenerClass, final CyNetwork network) {
		super(source, listenerClass);

		if (network == null)
			throw new NullPointerException("the \"network\" parameter must never be null!");
		this.net = network;
	}

	public final CyNetwork getNetwork() {
		return net;
	}
}

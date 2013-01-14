package org.cytoscape.application.events;


import java.util.List;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.model.CyNetwork;

/**
 * An event indicating the a collection of networks has been selected.
 * @CyAPI.Final.Class
 * @CyAPI.InModule application-api
 */
public final class SetSelectedNetworksEvent extends AbstractCyEvent<CyApplicationManager> {
	private final List<CyNetwork> networks;

	/**
	 * Constructor.
	 * @param source the application manager firing the event.
	 * @param networks the collection of networks that has been selected.
	 */
	public SetSelectedNetworksEvent(final CyApplicationManager source, final List<CyNetwork> networks) {
		super(source,SetSelectedNetworksListener.class);
		this.networks = networks;
	}

	/** 
	 * Returns the list of networks selected.
	 * @return The list of networks selected.
	 */
	public List<CyNetwork> getNetworks() {
		return networks;
	}
}

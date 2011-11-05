package org.cytoscape.view.model.events;

import java.util.Collection;

import org.cytoscape.event.AbstractCyPayloadEvent;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.view.model.CyNetworkView;

/**
 * #ASKMIKE
 * @CyAPI.Final.Class
 */
public final class NetworkViewChangedEvent extends AbstractCyPayloadEvent<CyNetworkView, ViewChangeRecord<CyNetwork>> {
	
	/**
	 * #ASKMIKE
	 * @param source
	 * @param networkViewChanges
	 */
	public NetworkViewChangedEvent(CyNetworkView source, Collection<ViewChangeRecord<CyNetwork>> networkViewChanges) {
		super(source, NetworkViewChangedListener.class, networkViewChanges);
	}

}

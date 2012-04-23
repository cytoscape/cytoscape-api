package org.cytoscape.view.model.events;

import java.util.Collection;

import org.cytoscape.event.AbstractCyPayloadEvent;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.view.model.CyNetworkView;

/**
 * Fired to indicated that a {@link CyNetworkView} has been modified in some way. 
 * @CyAPI.Final.Class
 */
public final class NetworkViewChangedEvent extends AbstractCyPayloadEvent<CyNetworkView, ViewChangeRecord<CyNetwork>> {
	
	/**
	 * Constructor. 
	 * @param source The network view that has changed.
	 * @param networkViewChanges A collection of view change records that describe the changes made to the 
	 * network view.
	 */
	public NetworkViewChangedEvent(CyNetworkView source, Collection<ViewChangeRecord<CyNetwork>> networkViewChanges) {
		super(source, NetworkViewChangedListener.class, networkViewChanges);
	}

}

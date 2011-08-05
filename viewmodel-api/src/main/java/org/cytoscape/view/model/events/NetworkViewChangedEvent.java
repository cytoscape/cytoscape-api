package org.cytoscape.view.model.events;

import java.util.Collection;

import org.cytoscape.event.AbstractCyPayloadEvent;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.view.model.CyNetworkView;

public final class NetworkViewChangedEvent extends AbstractCyPayloadEvent<CyNetworkView, ViewChangeRecord<CyNetwork>> {
	
	public NetworkViewChangedEvent(CyNetworkView source, Collection<ViewChangeRecord<CyNetwork>> networkViewChanges) {
		super(source, NetworkViewChangedListener.class, networkViewChanges);
	}

}

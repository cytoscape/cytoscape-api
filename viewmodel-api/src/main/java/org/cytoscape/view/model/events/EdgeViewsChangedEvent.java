package org.cytoscape.view.model.events;

import java.util.Collection;

import org.cytoscape.event.AbstractCyPayloadEvent;
import org.cytoscape.model.CyEdge;
import org.cytoscape.view.model.CyNetworkView;

public final class EdgeViewsChangedEvent extends AbstractCyPayloadEvent<CyNetworkView, ViewChangeRecord<CyEdge>> {
	
	public EdgeViewsChangedEvent(CyNetworkView source, Collection<ViewChangeRecord<CyEdge>> edgeViewChanges) {
		super(source, EdgeViewsChangedListener.class, edgeViewChanges);
	}

}

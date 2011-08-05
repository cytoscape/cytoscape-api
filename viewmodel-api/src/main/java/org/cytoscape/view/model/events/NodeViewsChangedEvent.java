package org.cytoscape.view.model.events;

import java.util.Collection;

import org.cytoscape.event.AbstractCyPayloadEvent;
import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.CyNetworkView;

public final class NodeViewsChangedEvent extends AbstractCyPayloadEvent<CyNetworkView, ViewChangeRecord<CyNode>> {
	
	public NodeViewsChangedEvent(CyNetworkView source, Collection<ViewChangeRecord<CyNode>> nodeViewChanges) {
		super(source, NodeViewsChangedListener.class, nodeViewChanges);
	}
}


package org.cytoscape.view.model.events;

import java.util.Collection;

import org.cytoscape.event.AbstractCyPayloadEvent;
import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.CyNetworkView;

/**
 * #ASKMIKE
 * @CyAPI.Final.Class
 */
public final class NodeViewsChangedEvent extends AbstractCyPayloadEvent<CyNetworkView, ViewChangeRecord<CyNode>> {
	
	/**
	 * #ASKMIKE
	 * @param source
	 * @param nodeViewChanges
	 */
	public NodeViewsChangedEvent(CyNetworkView source, Collection<ViewChangeRecord<CyNode>> nodeViewChanges) {
		super(source, NodeViewsChangedListener.class, nodeViewChanges);
	}
}


package org.cytoscape.view.model.events;

import java.util.Collection;

import org.cytoscape.event.AbstractCyPayloadEvent;
import org.cytoscape.model.CyEdge;
import org.cytoscape.view.model.CyNetworkView;

/**
 * #ASKMIKE
 * @CyAPI.Final.Class
 */
public final class EdgeViewsChangedEvent extends AbstractCyPayloadEvent<CyNetworkView, ViewChangeRecord<CyEdge>> {
	
	/**
	 * #ASKMIKE
	 * @param source
	 * @param edgeViewChanges
	 */
	public EdgeViewsChangedEvent(CyNetworkView source, Collection<ViewChangeRecord<CyEdge>> edgeViewChanges) {
		super(source, EdgeViewsChangedListener.class, edgeViewChanges);
	}

}

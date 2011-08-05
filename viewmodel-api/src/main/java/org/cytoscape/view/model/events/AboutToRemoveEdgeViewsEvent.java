package org.cytoscape.view.model.events;

import java.util.Collection;

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.event.AbstractCyPayloadEvent;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;
import org.cytoscape.model.CyEdge;

public class AboutToRemoveEdgeViewsEvent extends AbstractCyEvent<CyNetworkView> {
	
	private final Collection<View<CyEdge>> payload;
	
	public AboutToRemoveEdgeViewsEvent(CyNetworkView source, Collection<View<CyEdge>> payload) {
		super(source,AboutToRemoveEdgeViewsListener.class);
		if ( payload == null )
			throw new NullPointerException("edge view payload is null");
		this.payload = payload;
	}
	
	public Collection<View<CyEdge>> getEdgeViews() {
		return payload;
	}

}

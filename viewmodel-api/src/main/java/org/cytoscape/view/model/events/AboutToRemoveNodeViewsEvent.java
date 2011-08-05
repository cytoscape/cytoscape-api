package org.cytoscape.view.model.events;

import java.util.Collection;

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;
import org.cytoscape.model.CyNode;

public class AboutToRemoveNodeViewsEvent extends AbstractCyEvent<CyNetworkView> {

	private final Collection<View<CyNode>> payload;
	
	public AboutToRemoveNodeViewsEvent(CyNetworkView source, Collection<View<CyNode>> payload) {
		super(source,AboutToRemoveNodeViewsListener.class);		
		this.payload = payload;
	}
	
	public Collection<View<CyNode>> getNodeViews() {
		return payload;
	}
}

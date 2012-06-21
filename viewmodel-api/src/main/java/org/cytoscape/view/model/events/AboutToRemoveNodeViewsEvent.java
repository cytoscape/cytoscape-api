package org.cytoscape.view.model.events;

import java.util.Collection;

import org.cytoscape.event.AbstractCyPayloadEvent;
import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;

/**
 * When node {@link View}s are about to be removed from a {@linkplain CyNetworkView}, this event will be fired.
 * @CyAPI.Final.Class   
 */
public final class AboutToRemoveNodeViewsEvent extends AbstractCyPayloadEvent<CyNetworkView, View<CyNode>> {
	/**
	 * Creates the event for about to be removed node views.
	 * 
	 * @param source network view which includes the node views about to be removed.
	 * @param payload Collection of node view objects about to be removed.
	 * 
	 */
	public AboutToRemoveNodeViewsEvent(CyNetworkView source, Collection<View<CyNode>> payload) {
		super(source,AboutToRemoveNodeViewsListener.class, payload);		
	}
}

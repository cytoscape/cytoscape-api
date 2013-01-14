package org.cytoscape.view.model.events;

import java.util.Collection;

import org.cytoscape.event.AbstractCyPayloadEvent;
import org.cytoscape.model.CyEdge;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;

/**
 * When edge {@link View}s are about to be removed from a {@linkplain CyNetworkView}, this event will be fired.
 * @CyAPI.Final.Class 
 * @CyAPI.InModule viewmodel-api
 */
public final class AboutToRemoveEdgeViewsEvent extends AbstractCyPayloadEvent<CyNetworkView, View<CyEdge>> {
	/**
	 * Creates the event for about to be removed edge views.
	 * 
	 * @param source network view which includes the edge views about to be removed.
	 * @param payload Collection of edge view objects about to be removed.
	 * 
	 */
	public AboutToRemoveEdgeViewsEvent(CyNetworkView source, Collection<View<CyEdge>> payload) {
		super(source,AboutToRemoveEdgeViewsListener.class,payload);
	}
}


package org.cytoscape.view.model.events;

import java.util.Collection;

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.event.AbstractCyPayloadEvent;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;
import org.cytoscape.model.CyEdge;

/**
 * When edge {@linkplain View} is added to a {@linkplain CyNetworkView}, this event will be fired.
 * @CyAPI.Final.Class
 * @CyAPI.InModule viewmodel-api
 */
public final class AddedEdgeViewsEvent extends AbstractCyPayloadEvent<CyNetworkView,View<CyEdge>> {
	
	
	/**
	 * Creates the event for new edge views.
	 * 
	 * @param source network view which includes the new edge view.
	 * @param edgeViews Collection of newly created view object for an edge.
	 * 
	 */
	public AddedEdgeViewsEvent(final CyNetworkView source, final Collection<View<CyEdge>> edgeViews) {
		super(source, AddedEdgeViewsListener.class, edgeViews);
	}

	/**
	 * Returns new edge view added to the source network view object.
	 * 
	 * @return new edge view added to the network view.
	 * 
	 */
	public Collection<View<CyEdge>> getEdgeViews() {
		return getPayloadCollection();
	}
}

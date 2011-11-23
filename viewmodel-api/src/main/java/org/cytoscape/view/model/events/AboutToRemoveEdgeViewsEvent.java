package org.cytoscape.view.model.events;

import java.util.Collection;

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.event.AbstractCyPayloadEvent;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;
import org.cytoscape.model.CyEdge;

/**
 * When edge {@link View}s are about to be removed from a {@linkplain CyNetworkView}, this event will be fired.
 * @CyAPI.Final.Class 
 */
public final class AboutToRemoveEdgeViewsEvent extends AbstractCyEvent<CyNetworkView> {
	
	private final Collection<View<CyEdge>> payload;
	
	/**
	 * Creates the event for about to be removed edge views.
	 * 
	 * @param source network view which includes the edge views about to be removed.
	 * @param payload Collection of edge view objects about to be removed.
	 * 
	 */
	public AboutToRemoveEdgeViewsEvent(CyNetworkView source, Collection<View<CyEdge>> payload) {
		super(source,AboutToRemoveEdgeViewsListener.class);
		if ( payload == null )
			throw new NullPointerException("edge view payload is null");
		this.payload = payload;
	}
	
	/** 
	 * Returns the Collection of Views of type CyEdge that are about to be removed. 
	 * @return the Collection of Views of type CyEdge that are about to be removed. 
	 */
	public Collection<View<CyEdge>> getEdgeViews() {
		return payload;
	}

}

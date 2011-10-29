package org.cytoscape.view.model.events;

import java.util.Collection;

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;
import org.cytoscape.model.CyNode;

/**
 * When node {@link View}s are about to be removed from a {@linkplain CyNetworkView}, this event will be fired.
 * @CyAPI.Final.Class   #ASKMIKE : This class isn't declared as final.
 */
public class AboutToRemoveNodeViewsEvent extends AbstractCyEvent<CyNetworkView> {

	private final Collection<View<CyNode>> payload;
	
	/**
	 * Creates the event for about to be removed node views.
	 * 
	 * @param source network view which includes the node views about to be removed.
	 * @param payload Collection of node view objects about to be removed.
	 * 
	 */
	public AboutToRemoveNodeViewsEvent(CyNetworkView source, Collection<View<CyNode>> payload) {
		super(source,AboutToRemoveNodeViewsListener.class);		
		this.payload = payload;
	}
	
	/** 
	 * Returns a Collection of Views of type CyNode that are about to be removed. 
	 * @return a Collection of Views of type CyNode that are about to be removed. 
	 * */
	public Collection<View<CyNode>> getNodeViews() {
		return payload;
	}
}

package org.cytoscape.view.model.events;

import java.util.Collection;

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.event.AbstractCyPayloadEvent;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;
import org.cytoscape.model.CyNode;

/**
 * When node {@linkplain View} is added to a {@linkplain CyNetworkView}, this event will be fired.
 */
public final class AddedNodeViewsEvent extends AbstractCyPayloadEvent<CyNetworkView,View<CyNode>> {


	/**
	 * Creates the event for a new node view.
	 * 
	 * @param source network view which includes the new node view.
	 * @param nodeView Newly created view object for a node.
	 * 
	 */
	public AddedNodeViewsEvent(final CyNetworkView source, final Collection<View<CyNode>> nodeViews) {
		super(source, AddedNodeViewsListener.class,nodeViews);
	}

	/**
	 * Returns new node view object.
	 * 
	 * @return new node view
	 */
	public Collection<View<CyNode>> getNodeViews() {
		return getPayloadCollection();
	}
}

package org.cytoscape.view.model;

/*
 * #%L
 * Cytoscape View Model API (viewmodel-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2013 The Cytoscape Consortium
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

import java.util.Collection;

import org.cytoscape.model.CyDisposable;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

/**
 * 
 * Additional methods for CyNetworkView. Network view should implement BOTH View
 * and CyNetworkView.
 * 
 * <p>
 * <i>Warning</i>: if you just added a node or edge in
 * {@link org.cytoscape.model.CyNetwork},
 * {@link #getNodeView} or {@link #getEdgeView}
 * will probably return null for the newly created node or edge.
 * You may have to call
 * {@link org.cytoscape.event.CyEventHelper#flushPayloadEvents}
 * <i>prior</i> to calling {@link #getNodeView} or {@link #getEdgeView},
 * so that the {@code CyNetworkView} gets a chance to create the views.
 * If you are creating a bunch of nodes and edges at once, call {@code flushPayloadEvents}
 * <i>after</i> all the nodes and edges are created.
 * </p>
 * 
 * @CyAPI.Api.Interface
 * @CyAPI.InModule viewmodel-api
 */
public interface CyNetworkView extends View<CyNetwork>, CyDisposable {

	
	default CyNetworkView createSnapshot() {
		return null;
	};
	
	
	/**
	 * Returns a View for a specified Node.
     *
	 * 
	 * @param node Node object
     * 
	 * @return View for the given node object.
	 */
	View<CyNode> getNodeView(final CyNode node);

	/**
	 * Returns a list of Views for all CyNodes in the network.
	 * 
	 * @return Collection of all node views in this network.
	 */
	Collection<View<CyNode>> getNodeViews();

	/**
	 * Returns a View for a specified Edge.
	 * @param edge the edge to return the view for.
	 * 
	 * @return View model for the edge data.
	 */
	View<CyEdge> getEdgeView(final CyEdge edge);

	/**
	 * Returns a list of Views for all CyEdges in the network.
	 * 
	 * @return All edge views in this network.
	 */
	Collection<View<CyEdge>> getEdgeViews();

	/**
	 * Returns a list of all View including those for Nodes, Edges, and Network.
	 * 
	 * @return All view objects in this network including network view itself.
	 */
	Collection<View<? extends CyIdentifiable>> getAllViews();
	
	
	/**
	 * Utility method to fit content to the presentation container (usually a Swing Window).
	 * This fires event to the presentation layer for updating presentation.
	 */
	void fitContent();
	
	
	/**
	 * Utility method to fit selected graph objects to the presentation container.
	 * This fires event to the presentation layer for updating presentation.
	 */
	void fitSelected();
	
	
	/**
	 * Cascading event for the presentation layer for updating presentation.
	 */
	void updateView();

	
	public default boolean isDirty() {
		return false;
	}
	 
	/**
	 * Sets the default value to be used for the specified visual property.
	 * @param <T> The type of the visual property value. 
	 * @param <V> The default value for the visual property, which must extend T. 
	 * @param vp The visual property whose default value we're specifying.
	 * @param defaultValue The default value to be used for this visual property for this view. 
	 */
	 <T, V extends T> void setViewDefault(final VisualProperty<? extends T> vp, final V defaultValue);

	 
	 /**
	  * Returns the ID of the renderer that must be used to render this view.
	  * 
	  * @see org.cytoscape.application.NetworkViewRenderer#getId()
	  * @see org.cytoscape.application.CyApplicationManager#getNetworkViewRenderer(rendererId)
	  */
	 String getRendererId();
	 
	 
	 default void addNetworkViewListener(CyNetworkViewListener listener) {}
	 
	 default void removeNetworkViewListener(CyNetworkViewListener listener) {}
	 
}

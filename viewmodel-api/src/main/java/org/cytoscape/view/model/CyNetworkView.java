/*
 Copyright (c) 2008, The Cytoscape Consortium (www.cytoscape.org)

 The Cytoscape Consortium is:
 - Institute for Systems Biology
 - University of California San Diego
 - Memorial Sloan-Kettering Cancer Center
 - Institut Pasteur
 - Agilent Technologies

 This library is free software; you can redistribute it and/or modify it
 under the terms of the GNU Lesser General Public License as published
 by the Free Software Foundation; either version 2.1 of the License, or
 any later version.

 This library is distributed in the hope that it will be useful, but
 WITHOUT ANY WARRANTY, WITHOUT EVEN THE IMPLIED WARRANTY OF
 MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.  The software and
 documentation provided hereunder is on an "as is" basis, and the
 Institute for Systems Biology and the Whitehead Institute
 have no obligations to provide maintenance, support,
 updates, enhancements or modifications.  In no event shall the
 Institute for Systems Biology and the Whitehead Institute
 be liable to any party for direct, indirect, special,
 incidental or consequential damages, including lost profits, arising
 out of the use of this software and its documentation, even if the
 Institute for Systems Biology and the Whitehead Institute
 have been advised of the possibility of such damage.  See
 the GNU Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation,
 Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
 */
package org.cytoscape.view.model;

import java.util.Collection;

import org.cytoscape.model.CyDisposable;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyIdentifiable;

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

	 
	/**
	 * Sets the default value to be used for the specified visual property.
	 * @param <T> The type of the visual property value. 
	 * @param <V> The default value for the visual property, which must extend T. 
	 * @param vp The visual property whose default value we're specifying.
	 * @param defaultValue The default value to be used for this visual property for this view. 
	 */
	 <T, V extends T> void setViewDefault(final VisualProperty<? extends T> vp, final V defaultValue);

}

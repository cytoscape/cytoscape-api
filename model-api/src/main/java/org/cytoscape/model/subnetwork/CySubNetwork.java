package org.cytoscape.model.subnetwork;

/*
 * #%L
 * Cytoscape Model API (model-api)
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

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyEdge;

/**
 * A CySubNetwork is a {@link CyNetwork} that is contained within a parent
 * {@link CyNetwork}.  See the description in {@link CyRootNetwork} for
 * a more complete description of Cytoscape's meta-network model.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule model-api
 */
public interface CySubNetwork extends CyNetwork {
	/**
	 * Return the {@link CyRootNetwork} that contains this CySubNetwork. 
	 * @return  the {@link CyRootNetwork} that contains this CySubNetowrk.
	 */
	CyRootNetwork getRootNetwork(); 

	/**
	 * Adds a node to this {@link CySubNetwork}.  Note that the added node
	 * is not a new node and must already exist in the {@link CyRootNetwork}.
	 * @param node  CyNode to add to this subnetwork
	 * @return true if the node was successfully added to the subnetwork, 
	 * false otherwise.
	 */
	boolean addNode(CyNode node);

	/**
	 * Adds an edge to this {@link CySubNetwork}.  Note that the added edge
	 * is not a new edge and must already exist in the {@link CyRootNetwork}.
	 * If the nodes that define the source and target of the edge are not yet
	 * contained in the subnetwork, they will be added. The nodes must also
	 * already exist in the {@link CyRootNetwork}.
	 * @param edge  CyEdge to add to this subnetwork
	 * @return true if the edge was successfully added to the subnetwork, 
	 * false otherwise.
	 */
	boolean addEdge(CyEdge edge);

	/**
	 * A shortcut method that Creates a new {@link CyNode} in both this subnetwork 
	 * <b>AND</b> in the {@link CyRootNetwork}.
	 * @return A new CyNode that exists in both this subnetwork and the associated
	 * {@link CyRootNetwork}.
	 */
	CyNode addNode();

	/**
	 * A shortcut method that Creates a new {@link CyEdge} in both this subnetwork 
	 * <b>AND</b> in the {@link CyRootNetwork}.
	 * @param source The source node of the edge. The source node must exist
	 * in the root network.
	 * @param target The target node of the edge. The target node must exist
	 * in the root network.
	 * @param directed Whether the edge should be considered directed or not.
	 * @return A new CyEdge that exists in both this subnetwork and the associated
	 * {@link CyRootNetwork}.
	 */
	CyEdge addEdge(CyNode source, CyNode target, boolean directed);

	/**
	 * Removes a node from this {@link CySubNetwork} but not from the {@link CyRootNetwork}.  
	 * The node is removed from the CySubNetwork, but <i>not</i> deleted
	 * from the {@link CyRootNetwork}.
	 * @param node  Node to remove from this subnetwork
	 * @return true if the node was successfully removed from the specified subnetwork,
	 * false otherwise.
	 */
	boolean removeNodes(Collection<CyNode> node);

	/**
	 * Removes a edge from this {@link CySubNetwork} but not from the {@link CyRootNetwork}.  
	 * The edge is removed from the CySubNetwork, but <i>not</i> deleted
	 * from the {@link CyRootNetwork}.
	 * @param edge  Edge to remove from this subnetwork
	 * @return true if the edge was successfully removed from the specified subnetwork,
	 * false otherwise.
	 */
	boolean removeEdges(Collection<CyEdge> edge);
}

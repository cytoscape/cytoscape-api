package org.cytoscape.group;

/*
 * #%L
 * Cytoscape Groups API (group-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2021 The Cytoscape Consortium
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

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.subnetwork.CyRootNetwork;

import java.util.List;
import java.util.Set;


/**
 * An object that represents a group of nodes and edges.  A CyGroup
 * is an object that encapsulates a {@link CyNode} that represents
 * a collection of {@link CyNode}s and connecting {@link CyEdge}s
 * stored as a {@link CyNetwork}.  In addition a CyGroup maintains
 * information about the links between the nodes in the group and
 * nodes external to the group.  Groups may be collapsed or expanded.
 * A collapsed group is represented in a network as the {@link CyNode}
 * representing the group and a series of "meta" edges that represent
 * the edges between nodes within the group and external nodes.  Like
 * any other node, the group node can also have edges connecting to other
 * nodes.
 *
 * @CyAPI.Api.Interface
 * @CyAPI.InModule group-api
 */
public interface CyGroup {
	/**
	 * Return the {@link CyNode} that represents this group
	 * in the network.
	 *
	 * @return the {@link CyNode} for this group
	 */
	CyNode getGroupNode();

	/**
	 * Returns the list of nodes contained within this group.
	 * 
	 * @return A list of CyNodes
	 * returns null otherwise.
	 */
	List<CyNode> getNodeList();

	/**
	 * Returns the list of edges contained within this group.
	 * 
	 * @return A list of CyEdges
	 */
	List<CyEdge> getInternalEdgeList();

	/**
	 * Returns the list of exterior edges connected to the
	 * nodes within this group.
	 * 
	 * @return A list of CyEdges
	 */
	Set<CyEdge> getExternalEdgeList();

	/**
	 * Returns the network referenced by this group.  All
	 * groups will point to a network, although the network
	 * might not have any nodes or edges.  This is the internal
	 * network representation of the group, not the networks that
	 * the group is a member of.
	 * 
	 * @return A reference to a CyNetwork
	 */
	CyNetwork getGroupNetwork();

	/**
	 * Add a list of nodes and their edges
	 *
	 * @param nodes the list of nodes to add
	 */
	void addNodes(List<CyNode> nodes);

	/**
	 * Add a list of edges to a group.  The edges
	 * will be added either to the internal edges
	 * or external edges lists.  Note that unlike
	 * {@link CyGroup#addNodes} either the source
	 * node or target node for the edge must be
	 * in the group.
	 *
	 * @param edges the list of edges to add
	 */
	void addEdges(List<CyEdge> edges);

	/**
	 * Remove a set of nodes and their edges from a group
	 *
	 * @param nodes the nodes to remove
	 */
	void removeNodes(List<CyNode> nodes);

	/**
	 * Remove a set of edges from a group.  Edges can be either
	 * internal edges or external edges.
	 *
	 * @param edges the edges to remove
	 */
	void removeEdges(List<CyEdge> edges);

	/**
	 * Return the root network for this group.  A group can only
	 * exist within one root network, and all nodes and edges
	 * that are part of the group must exist within that root network
	 * also.
	 *
	 * @return the root network for this group
	 */
	CyRootNetwork getRootNetwork(); // Should this return a CyNetwork instead?

	/**
	 * Add a group to an additional network.  When groups are created, 
	 * they are created in a particular network.  The can also be shown 
	 * in other networks, as long as all of
	 * the networks are in the same {@link CyRootNetwork}
	 *
	 * @param network the {@link CyNetwork} to add the group to
	 */
	public void addGroupToNetwork(CyNetwork network);

	/**
	 * Remove a group from a network.
	 *
	 * @param network the {@link CyNetwork} to remove the group from
	 */
	public void removeGroupFromNetwork(CyNetwork network);

	/**
	 * Return the list of {@link CyNetwork}s this group is in.
	 *
	 * @return the set of {@link CyNetwork}s that contain this group
	 */
	public Set<CyNetwork> getNetworkSet();

	/**
 	 * Check to see if this group is defined in a particular network.
 	 *
 	 * @param network the {@link CyNetwork} to test
 	 * @return true if the group is in that network
 	 */
	public boolean isInNetwork(CyNetwork network);

	/**
	 * Collapse this group in the designated network.  Note that this is 
	 * a "model-level" collapse, which removes all of the member nodes 
	 * and edges and replaces them with the node that represents the 
	 * group in the network.  This does not imply a specific visual 
	 * representation.
	 *
	 * @param network the {@link CyNetwork} that this group should 
	 *                be collapsed in
	 */
	void collapse(CyNetwork network);

	/**
	 * Expand this group in the designated network.  Note that this 
	 * is a "model-level" expansion, which removes the node that 
	 * represents the group from the network and adds all of the 
	 * member nodes and edges. This does not imply a specific 
	 * visual representation.
	 *
	 * @param network the {@link CyNetwork} that this group 
	 *                 should be expanded in
	 */
	void expand(CyNetwork network);

	/**
	 * Return whether this group is expanded or collapsed in the 
	 * designated network.
	 *
	 * @param network the {@link CyNetwork} we're interested in
	 * @return true if the group is collapsed in that network.
	 */
	boolean isCollapsed(CyNetwork network);

}

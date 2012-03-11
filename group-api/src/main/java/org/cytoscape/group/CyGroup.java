/*
 Copyright (c) 2008, 2010, The Cytoscape Consortium (www.cytoscape.org)

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
package org.cytoscape.group;

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.subnetwork.CyRootNetwork;

import java.util.List;
import java.util.Set;


/**
 * An object that represents a group of nodes and edges.  Groups at the model
 * layer are a simple extension of a CyNode that creates a subnetwork for every
 * for every group.  Groups may be added to networks in the same way that nodes
 * are.  To determine if a node in a network is a group, use the instanceof keyword
 * to check for CyGroup: if (node instanceof CyGroup).
 *
 * @CyAPI.Api.Interface
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
	 * Add a node to a group
	 *
	 * @param node the node to add
	 */
	void addNode(CyNode node);

	/**
	 * Add an internal edge to a group.  Both the source and destination
	 * nodes must already be added.
	 *
	 * @param edge the edge to add
	 */
	void addInternalEdge(CyEdge edge);

	/**
	 * Add an external edge to a group.  One of the source and destination
	 * nodes must already be added.
	 *
	 * @param edge the edge to add
	 */
	void addExternalEdge(CyEdge edge);

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
	 * Add a group to an additional network.  When groups are created, they are created in
	 * a particular network.  The can also be shown in other networks, as long as all of
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
	 * Collapse this group in the designated network.  Note that this is a "model-level"
	 * collapse, which removes all of the member nodes and edges and replaces them
	 * with the node that represents the group in the network.  This does not imply
	 * a specific visual representation.
	 *
	 * @param network the {@link CyNetwork} that this group should be collapsed in
	 */
	void collapse(CyNetwork network);

	/**
	 * Expand this group in the designated network.  Note that this is a "model-level"
	 * expansion, which removes the node that represents the group from the network
	 * and adds all of the member nodes and edges. This does not imply
	 * a specific visual representation.
	 *
	 * @param network the {@link CyNetwork} that this group should be expanded in
	 */
	void expand(CyNetwork network);

	/**
	 * Return whether this group is expanded or collapsed in the designated network.
	 *
	 * @param network the {@link CyNetwork} we're interested in
	 * @return true if the group is collapsed in that network.
	 */
	boolean isCollapsed(CyNetwork network);

}

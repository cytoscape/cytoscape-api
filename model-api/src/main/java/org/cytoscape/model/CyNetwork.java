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
package org.cytoscape.model;


import java.util.Collection;
import java.util.List;


/**
 * CyNetwork is the primary interface for representing a network (graph) 
 * data structure in Cytoscape. Specifically, CyNetwork represents a
 * multi-graph as multiple edges may exist between nodes. Edges may
 * be directed, undirected, or both.
 * @CyAPI.Api.Interface
 */
public interface CyNetwork extends CyTableEntry {
	/**
	 * A boolean column created by default for every CyNode or CyEdge that
	 * holds the selection state of the entry. 
	 */
	String SELECTED = "selected";

	/**
	 * The name of the default <b>public</b> 
	 * CyTable that is created by default for CyNetworks, 
	 * CyNodes, and CyEdges.  Other CyTables may also be associated with networks
	 * -- see {@link CyTableManager} for more information.
	 * The table should be referenced using this constant:
	 * <code>CyNetwork.DEFAULT_ATTRS</code>.
	 */
	String DEFAULT_ATTRS = "USER";

	/**
	 * The name of the default <b>hidden</b> CyTable that is created 
	 * by default for CyNetworks, CyNodes, and
	 * CyEdges.  Other CyTables may also be associated with networks -- 
	 * see {@link CyTableManager} for more information.
	 */
	String HIDDEN_ATTRS = "HIDDEN";

	/**
	 * This method is used to create and add a node to this network.
	 *
	 * @return the created node
	 */
	CyNode addNode();

	/**
	 * Remove a node from the network and delete the node (if it only exists in 
	 * this network).  See {@link org.cytoscape.model.subnetwork.CyRootNetwork} 
	 * for information about having the same node in two networks.
	 *
	 * @param node the node to be deleted
	 * @return true if the node was successfully deleted
	 */
	boolean removeNodes(Collection<CyNode> node);

	/**
	 * This method is used to create and add an edge to this network.
	 *
	 * @param source the source (or start) of the edge
	 * @param target the target (or end) of the edge
	 * @param isDirected if 'true' this is a directed edge
	 * @return the created edge
	 */
	CyEdge addEdge(CyNode source, CyNode target, boolean isDirected);

	/**
	 * Remove an edge from the network and delete the edge (if it only exists in
	 * this network).  See {@link org.cytoscape.model.subnetwork.CyRootNetwork} 
	 * for information about having the same edge in two networks.
	 *
	 * @param edges the edges to be deleted
	 * @return true if the edge was successfully deleted
	 */
	boolean removeEdges(Collection<CyEdge> edges);

	/**
	 * Return the number of nodes in this network.
	 *
	 * @return the number of nodes
	 */
	int getNodeCount();

	/**
	 * Return the number of edges in this network.
	 *
	 * @return the number of edges
	 */
	int getEdgeCount();

	/**
	 * Return a list of the nodes in this network.
	 * The list will be empty if there are no nodes in the network.
	 * This method should never return null.
	 * Modifying this list (if allowed by the implementation)
	 * has no effect on the network.
	 *
	 * @return the node list
	 */
	List<CyNode> getNodeList();

	/**
	 * Return a list of the edges in this network.
	 * The list will be empty if there are no edges in the network.
	 * This method should never return null.
	 * Modifying this list (if allowed by the implementation)
	 * has no effect on the network.
	 *
	 * @return the edge list
	 */
	List<CyEdge> getEdgeList();

	/**
	 * Determine if this CyNetwork contains a particular node.
	 *
	 * @param node the node to check
	 * @return true if this network contains the node
	 */
	boolean containsNode(CyNode node);

	/**
	 * Determine if this CyNetwork contains a particular edge.
	 *
	 * @param edge the edge to check
	 * @return true if this network contains the edge
	 */
	boolean containsEdge(CyEdge edge);

	/**
	 * Determine if this CyNetwork contains an edge between
	 * two nodes.  Note that if the edge is directed, the
	 * source and targets must match.
	 *
	 * @param  from the source of the edge
	 * @param  to the target of the edge
	 * @return true if this network contains the edge
	 */
	boolean containsEdge(CyNode from, CyNode to);

	/**
	 * Return the CyNode that has the index. To iterate
	 * over all nodes, iterate over getNodeList(). We make
	 * no guarantees on what represent valid values for the index.
	 * The only valid indices are those accessed from existing 
	 * nodes.
	 *
	 * @param index the index of the CyNode to get
	 * @return the associated CyNode or null if there is no
	 * node with that index in this network.
	 */
	CyNode getNode(int index);

	/**
	 * Return the CyEdge that has the index. To iterate
	 * over all edges, iterate over getEdgeList(). We make
	 * no guarantees on what represent valid values for the index.
	 * The only valid indices are those accessed from existing 
	 * edges.
	 *
	 * @param index the index of the CyEdge to get
	 * @return the associated CyEdge or null if there is no
	 * edge with that index in this network.
	 */
	CyEdge getEdge(int index);

	/**
	 * Get the list of nodes that neighbor this node where the
	 * definition of "neighbor" is a node that is connected to this
	 * node by the passed edgeType.  The {@link CyEdge.Type} enum is
	 * used to determine whether the list includes undirected, directed,
	 * incoming, or outgoing edges. The list will be empty if there 
	 * are no neighbor nodes found.
	 * This method should never return null.
	 * Modifying this list (if allowed by the implementation)
	 * has no effect on the network.
	 *
	 * @param node the node whose neighbors we're looking for
	 * @param edgeType the directionality of the edges we're interested in
	 * @return the list of nodes that neighbor this node
	 */
	List<CyNode> getNeighborList(CyNode node, CyEdge.Type edgeType);

	/**
	 * Get the list of edges that connect to this node. The {@link CyEdge.Type} enum is
	 * used to determine whether the list includes undirected, directed,
	 * incoming, or outgoing edges. The list will be empty if there are
	 * no adjacent edges found.
	 * This method should never return null.
	 * Modifying this list (if allowed by the implementation)
	 * has no effect on the network.
	 *
	 * @param node the node whose edges we're looking for
	 * @param edgeType the directionality of the edges we're interested in
	 * @return the list of edges that are adjacent to this one
	 */
	List<CyEdge> getAdjacentEdgeList(CyNode node, CyEdge.Type edgeType);

	/**
	 * Gets an Iteable of edges that connect to this node. The {@link CyEdge.Type} enum is
	 * used to determine whether the list includes undirected, directed,
	 * incoming, or outgoing edges. The iterable will be empty if there are
	 * no adjacent edges found.
	 * This method should never return null.
	 * Modifying this list (if allowed by the implementation)
	 * has no effect on the network.
	 * The Iterable implementation <i>may</i> be more efficient because it doesn't
	 * necessarily need to create a list first. However, that depends on implementation
	 * details, so you should evaluate the method performance yourself.
	 *
	 * @param node the node whose edges we're looking for
	 * @param edgeType the directionality of the edges we're interested in
	 * @return the Iterable of edges that are adjacent to this one
	 */
	Iterable<CyEdge> getAdjacentEdgeIterable(CyNode node, CyEdge.Type edgeType);

	/**
	 * Get the list of edges that connect two nodes.  The {@link CyEdge.Type} enum is
	 * used to determine whether the list includes undirected, directed,
	 * incoming, or outgoing edges. The list will be empty if no connecting
	 * edges are found.
	 * This method should never return null.
	 * Modifying this list (if allowed by the implementation)
	 * has no effect on the network.
	 *
	 * @param source the source node
	 * @param target the target node
	 * @param edgeType the directionality of the edges we're interested in
	 * @return the list of edges that include source and target and directed edges.
	 */
	List<CyEdge> getConnectingEdgeList(CyNode source, CyNode target, CyEdge.Type edgeType);

	/**
	 * A convenience method returns the default attribute table for this network.
	 * This is equivalent to 
	 * <code>cyTableManager.getTableMap(CyNetwork.class,this).get(CyNetwork.DEFAULT_ATTRS);</code>
	 * @return The default attribute table for this network.
	 */
	CyTable getDefaultNetworkTable();

	/**
	 * A convenience method returns the default attribute table for the nodes of this network.
	 * This is equivalent to 
	 * <code>cyTableManager.getTableMap(CyNode.class,this).get(CyNetwork.DEFAULT_ATTRS);</code>
	 * @return The default attribute table for the nodes of this network.
	 */
	CyTable getDefaultNodeTable();

	/**
	 * A convenience method returns the default attribute table for the edges of this network.
	 * This is equivalent to 
	 * <code>cyTableManager.getTableMap(CyEdge.class,this).get(CyNetwork.DEFAULT_ATTRS);</code>
	 * @return The default attribute table for the edges of this network.
	 */
	CyTable getDefaultEdgeTable();

	
	/**
	 * Returns the row for the specified table name for this object.
	 * A null entry or a
	 * an entry not found in this network will return null.
	 * @param entry The entry (node, edge, network) whose row we're looking for. 
	 * @param tableName the name of the table from which to extract the row..
	 * @return the row in the table of the specified name for this object. 
	 */
	CyRow getCyRow(CyTableEntry entry, String tableName);
 
	/**
	 * A convenience method that returns the row in the default table 
	 * for this object. This method is equivalent to calling 
	 * getCyRow(entry,{@link CyNetwork#DEFAULT_ATTRS}). 
	 * A null entry or a
	 * an entry not found in this network will return null.
	 * @param entry The entry (node, edge, network) whose row we're looking for. 
	 * @return the row in the default table for this object. 
	 */
	CyRow getCyRow(CyTableEntry entry);

}

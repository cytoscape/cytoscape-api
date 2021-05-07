package org.cytoscape.model.subnetwork;

import java.util.List;

/*
 * #%L
 * Cytoscape Model API (model-api)
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
import org.cytoscape.model.CyTable;
import org.cytoscape.model.SavePolicy;


/**
 * CyRootNetwork is an interface for managing Cytoscape's 
 * meta-network implementation.  While most applications (and users!)
 * will treat each {@link CyNetwork} created within Cytoscape
 * as an independent network, beginning with Cytoscape 3.0, 
 * Cytoscape has provided a mechanism for implementing a more
 * complex meta-network structure that can be used for a variety
 * of other use cases, including implementing subnetworks
 * and shared nodes between subnetworks.  Beyond the concepts
 * and methods provided by {@link CyNetwork} a meta-network
 * adds three new concepts:
 * <ul><li>A <b>CyRootNetwork</b> is a {@link CyNetwork} that
 * adds methods for maintaining the meta-network.
 * All {@link CyNode}s and {@link CyEdge}s in all {@link CySubNetwork}s
 * that are part of this CyRootNetwork.
 * <li>A {@link CySubNetwork} is a set of nodes and edges
 * that comprise a sub-network of a {@link CyRootNetwork}.  
 * A {@link CySubNetwork} may be thought of as a projection of 
 * the graph implemented by the <b>CyRootNetwork</b>.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule model-api
 */
public interface CyRootNetwork extends CyNetwork {

	/**
	 * The name of the table containing the attributes shared
	 * by all subnetworks of this root network.  
	 * Direct use of this table is discouraged!
	 */
	String SHARED_ATTRS = "SHARED_ATTRS";

	/**
	 * The name of the table that enables sharing default attributes
	 * with all subnetworks.
	 * Direct use of this table is discouraged!
	 */
	String SHARED_DEFAULT_ATTRS = "SHARED_DEFAULT_ATTRS";

	/**
	 * The name of the shared name column found in the SHARED_ATTRS table. This
	 * column value will be updated any time the value of the CyNetwork.NAME 
	 * column in a default table is set. The purpose of this column is to serve
	 * as a join key for virtual columns to be added to the shared table. 
	 */
	String SHARED_NAME = "shared name";
	
	/**
	 * The name of the shared interaction column found in the SHARED_ATTRS table. This
	 * column value will be updated any time the value of the CyNetwork.EDGE 
	 * column in a default table is set. 
	 */
	String SHARED_INTERACTION = "shared interaction";

	/**
	 * Create an empty {@link CySubNetwork}.
	 * The new subnetwork is created with the same save policy of its root network. If you want to set a different 
	 * save policy to the new subnetwork, just use {@link #addSubNetwork(SavePolicy)}.
	 * @return  The created {@link CySubNetwork}.
	 */
	CySubNetwork addSubNetwork();
	
	/**
	 * Create an empty {@link CySubNetwork} which can have a different save policy from that of this root network,
	 * as long as the root network's policy is not {@link SavePolicy#DO_NOT_SAVE}.
	 * @param policy the save policy to follow during the life-cycle of the CyNetwork.
	 * @return The created {@link CySubNetwork}.
	 * @throws IllegalArgumentException if the save policy of this root network is
	 *         {@link SavePolicy#DO_NOT_SAVE}, and the policy argument is not the same.
	 */
	CySubNetwork addSubNetwork(SavePolicy policy);

	/**
	 * Create a {@link CySubNetwork} containing the specified {@link CyNode}s and
	 * {@link CyEdge}s. The nodes and edges must already exist in this root network.
	 * Nodes defining the source and target of edges that have not yet been added
	 * to the subnetwork will be added.
	 * The new subnetwork is created with the same save policy of its root network. If you want to set a different 
	 * save policy to the new subnetwork, just use {@link #addSubNetwork(Iterable, Iterable, SavePolicy)}.
	 * @param nodes The nodes to be added to the network. May be null or empty.
	 * @param edges The edges to be added to the network. May be null or empty.
	 * @return  The created {@link CySubNetwork}.
	 */
	CySubNetwork addSubNetwork(Iterable<CyNode> nodes, Iterable<CyEdge> edges);
	
	/**
	 * Create a {@link CySubNetwork} containing the specified {@link CyNode}s and
	 * {@link CyEdge}s. The nodes and edges must already exist in this root network.
	 * Nodes defining the source and target of edges that have not yet been added
	 * to the subnetwork will be added.
	 * The new {@link CySubNetwork} can have a different save policy from that of this root network, as long as the
	 * root network's policy is not {@link SavePolicy#DO_NOT_SAVE}.
	 * @param nodes The nodes to be added to the network. May be null or empty.
	 * @param edges The edges to be added to the network. May be null or empty.
	 * @param policy the save policy to follow during the life-cycle of the CyNetwork.
	 * @return  The created {@link CySubNetwork}.
	 * @throws IllegalArgumentException if the save policy of this root network is
	 *         {@link SavePolicy#DO_NOT_SAVE}, and the policy argument is not the same.
	 */
	CySubNetwork addSubNetwork(Iterable<CyNode> nodes, Iterable<CyEdge> edges, SavePolicy policy);

	/**
	 * Removes the subnetwork from the root network, but not the nodes and edges contained 
	 * in the subnetwork.
	 * @param sub  the {@link CySubNetwork} to remove.
	 */
	void removeSubNetwork(CySubNetwork sub);

	/**
	 * Will return A list of all {@link CySubNetwork}s contained in this root network.
	 * @return A list of all {@link CySubNetwork}s contained in this root network.
	 */
	List<CySubNetwork> getSubNetworkList();

	/**
	 * The initial network of {@link CyNode}s and {@link CyEdge}s. Every root network
	 * began with something, and this network is that something.
	 * @return the {@link CySubNetwork} that the root network began with.
	 */
	CySubNetwork getBaseNetwork();

	/**
	 * Returns true if the network to be checked is a subnetwork of this root network and
	 * returns false otherwise. Will return false if the network is null.
	 * @param n The network to be checked.
	 * @return true if the network to be checked is a subnetwork of this root network and
	 * returns false otherwise or if the network is null.
	 */
	boolean containsNetwork(CyNetwork n);

	/**
	 * Returns the network table shared by all subnetworks. The columns of this table 
	 * are automatically made into virtual columns of the DEFAULT network table of 
	 * each subnetwork of this root network.
	 * @return the network table shared by all subnetworks.
	 */
	CyTable getSharedNetworkTable();

	/**
	 * Returns the node table shared by all subnetworks. The columns of this table 
	 * are automatically made into virtual columns of the DEFAULT node table of 
	 * each subnetwork of this root network.
	 * @return the network table shared by all subnetworks.
	 */
	CyTable getSharedNodeTable();

	/**
	 * Returns the edge table shared by all subnetworks. The columns of this table 
	 * are automatically made into virtual columns of the DEFAULT edge table of 
	 * each subnetwork of this root network.
	 * @return the network table shared by all subnetworks.
	 */
	CyTable getSharedEdgeTable();
	
	/**
	 * Causes the given node to (temporarily) remain in the root network even if it has already been removed from all subnetworks.
	 * <br><br>
	 * The root network will automatically remove nodes/edges from itself that have been removed
	 * from all subnetworks. Calling this method <b>after</b> removing the node from all sub-networks
	 * will cause it to remain part of the root-network.
	 * <br><br>
	 * <b>Note:</b> No effort is made to track nodes/edges in the root network have been restored.
	 * Certain actions may cause them to be auto-deleted again. If a node is deleted from a subnetwork that is attached 
	 * to a restored edge then the edge may be auto-deleted from the root again. If an entire subnetwork is deleted then 
	 * all restored nodes/edges are considered for auto-deletion again. To force nodes/edges to never be deleted from the
	 * root network put them in a hidden subnetwork using {@link CyRootNetwork#addSubNetwork(Iterable, Iterable)}.
	 */
	void restoreNode(CyNode node);
	
	/**
	 * Causes the given edge to (temporarily) remain in the root network even if it has already been removed from all subnetworks.
	 * <br><br>
	 * The root network will automatically remove nodes/edges from itself that have been removed
	 * from all subnetworks. Calling this method <b>after</b> removing the edge from all sub-networks
	 * will cause it to remain part of the root-network.
	 * <br><br>
	 * <b>Note:</b> No effort is made to track nodes/edges in the root network have been restored.
	 * Certain actions may cause them to be auto-deleted again. If a node is deleted from a subnetwork that is attached 
	 * to a restored edge then the edge may be auto-deleted from the root again. If an entire subnetwork is deleted then 
	 * all restored nodes/edges are considered for auto-deletion again. To force nodes/edges to never be deleted from the
	 * root network put them in a hidden subnetwork using {@link CyRootNetwork#addSubNetwork(Iterable, Iterable)}.
	 */
	void restoreEdge(CyEdge edge);
	
}

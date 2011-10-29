
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

package org.cytoscape.model.subnetwork;

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

import java.util.List;


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
 */
public interface CyRootNetwork extends CyNetwork {

	/**
	 * Create an empty {@link CySubNetwork}. 
	 * @return  The created {@link CySubNetwork}.
	 */
	CySubNetwork addSubNetwork();

	/**
	 * Create a {@link CySubNetwork} containing the specified {@link CyNode}s and
	 * {@link CyEdge}s. The nodes and edges must already exist in this root network.
	 * Nodes defining the source and target of edges that have not yet been added
	 * to the subnetwork will be added.
	 * @param nodes The nodes to be added to the network. May be null or empty.
	 * @param edges The edges to be added to the network. May be null or empty.
	 * @return  The created {@link CySubNetwork}.
	 */
	CySubNetwork addSubNetwork(Iterable<CyNode> nodes, Iterable<CyEdge> edges);

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
}

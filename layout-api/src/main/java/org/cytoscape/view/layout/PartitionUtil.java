/* vim: set ts=2: */
/**
 * Copyright (c) 2006 The Regents of the University of California.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *   1. Redistributions of source code must retain the above copyright
 *      notice, this list of conditions, and the following disclaimer.
 *   2. Redistributions in binary form must reproduce the above
 *      copyright notice, this list of conditions, and the following
 *      disclaimer in the documentation and/or other materials provided
 *      with the distribution.
 *   3. Redistributions must acknowledge that this software was
 *      originally developed by the UCSF Computer Graphics Laboratory
 *      under support by the NIH National Center for Research Resources,
 *      grant P41-RR01081.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDER "AS IS" AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE REGENTS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */
package org.cytoscape.view.layout;

import org.cytoscape.model.CyTableUtil;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.View;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.presentation.property.MinimalVisualLexicon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;

import java.awt.Dimension;
import org.cytoscape.work.TaskMonitor;


/**
 * This class also provides static methods that are used to partition a network. 
 * @CyAPI.Static.Class
 */
public final class PartitionUtil {

	private static final int m_NODE_HAS_NOT_BEEN_SEEN = 0;
	private static final int m_NODE_HAS_BEEN_SEEN = 1;

	private PartitionUtil() {};


	/**
	 * Partition the graph -- this builds the LayoutEdge and LayoutNode
	 * arrays as a byproduct.  The algorithm for this was taken from
	 * algorithms/graphPartition/SGraphPartition.java.
	 *
	 * @param networkView the CyNetworkView representing the graph
	 * @param selectedOnly only consider selected nodes
	 * @param edgeWeighter the weighter to use for edge weighting
	 * @return a List of LayoutPartitions
	 */
	public static List<LayoutPartition> partition(CyNetworkView networkView,
	                             boolean selectedOnly, EdgeWeighter edgeWeighter) {

		if (selectedOnly)
			return partition(networkView, CyTableUtil.getNodesInState(networkView.getModel(),"selected",true),edgeWeighter);
		else
			return partition(networkView,networkView.getModel().getNodeList(),edgeWeighter);
	}

	/**
	 * Partition the graph -- this builds the LayoutEdge and LayoutNode
	 * arrays as a byproduct.  The algorithm for this was taken from
	 * algorithms/graphPartition/SGraphPartition.java.
	 *
	 * @param networkView the CyNetworkView representing the graph
	 * @param nodeSet the set of nodes to consider
	 * @param edgeWeighter the weighter to use for edge weighting
	 * @return a List of LayoutPartitions
	 */
	public static List<LayoutPartition> partition(CyNetworkView networkView,
	                             Collection<CyNode> nodeSet, EdgeWeighter edgeWeighter) {
		List<LayoutPartition> partitions = new ArrayList<LayoutPartition>();

		final CyNetwork network = networkView.getModel();

		Map<Integer,Integer> nodesSeenMap = new HashMap<Integer,Integer>(); 
		Map<Integer,Integer> edgesSeenMap = new HashMap<Integer,Integer>(); 
		Map<Integer,View<CyNode>> nodesToViews = new HashMap<Integer,View<CyNode>>(); 

		// Initialize the maps
		for (View<CyNode> nv: networkView.getNodeViews()){
			int node = nv.getModel().getIndex();
			nodesSeenMap.put(node, m_NODE_HAS_NOT_BEEN_SEEN);
			nodesToViews.put(node, nv);
		}

		for (CyEdge edge: network.getEdgeList()) {
			int edgeIndex = edge.getIndex();
			edgesSeenMap.put(edgeIndex, m_NODE_HAS_NOT_BEEN_SEEN);
		}

		// OK, now traverse the graph
		for (CyNode node: nodeSet) {
			int nodeIndex = node.getIndex();

			// Have we seen this already?
			if (nodesSeenMap.get(nodeIndex) == m_NODE_HAS_BEEN_SEEN)
				continue;

			// Nope, first time
			LayoutPartition part = new LayoutPartition(network.getNodeCount(),
			                                           network.getEdgeCount());
			// Set the edge weighter
			part.setEdgeWeighter(edgeWeighter);

			nodesSeenMap.put(nodeIndex, m_NODE_HAS_BEEN_SEEN);

			// Traverse through all connected nodes
			traverse(network, networkView, nodesToViews, node, part, nodesSeenMap, edgesSeenMap);

			// Done -- finalize the parition
			part.trimToSize();

			// Finally, now that we're sure we've touched all of our
			// nodes.  Fix up our edgeLayout list to have all of our
			// layoutNodes
			part.fixEdges();

			partitions.add(part);
		}

		// Now sort the partitions based on the partition's node count
		Collections.sort(partitions,
			new Comparator<LayoutPartition>() {
				public int compare(LayoutPartition p1, LayoutPartition p2) {
					return (p2.size() - p1.size());
				}

				public boolean equals(LayoutPartition obj) {
					return false;
				}
			});

		return partitions; 
	}

	/**
	  * This method traverses nodes connected to the specified node.
	  * @param network            The GraphPerspective we are laying out
	  * @param networkView        The CyNetworkView we are laying out
	  * @param nodesToViews       A map that maps between nodes and views
	  * @param node               The node to search for connected nodes.
	  * @param partition          The partition that we're laying out
	  * @param nodesSeenMap       A map of nodes already visited.
	  * @param edgesSeenMap       A map of edges already visited.
	  */
	private static void traverse(CyNetwork network, CyNetworkView networkView,
	                             Map<Integer,View<CyNode>> nodesToViews, CyNode node,
	                             LayoutPartition partition, Map<Integer,Integer> nodesSeenMap, 
								 Map<Integer,Integer> edgesSeenMap ) {
		int nodeIndex = node.getIndex();

		// Get the View<CyNode>
		View<CyNode> nv = nodesToViews.get(nodeIndex);

		// Add this node to the partition
		partition.addNode(nv, false);

		// Iterate through each connected edge
		for (CyEdge incidentEdge: network.getAdjacentEdgeList(node, CyEdge.Type.ANY)){

			int edgeIndex = incidentEdge.getIndex();

			// Have we already seen this edge?
			if (edgesSeenMap.get(edgeIndex) == m_NODE_HAS_BEEN_SEEN) {
				// Yes, continue since it means we *must* have seen both sides
				continue;
			}

			edgesSeenMap.put(edgeIndex, m_NODE_HAS_BEEN_SEEN);

			// Add the edge to the partition
			partition.addEdge(incidentEdge);

			// Determine the node's index that is on the other side of the edge
			CyNode otherNode;

			if (incidentEdge.getSource() == node) {
				otherNode = incidentEdge.getTarget();
			} else {
				otherNode = incidentEdge.getSource();
			}

			int incidentNodeIndex = otherNode.getIndex();

			// Have we seen the connecting node yet?
			if (nodesSeenMap.get(incidentNodeIndex) == m_NODE_HAS_NOT_BEEN_SEEN) {
				// Mark it as having been seen
				nodesSeenMap.put(incidentNodeIndex, m_NODE_HAS_BEEN_SEEN);

				// Traverse through this one
				traverse(network, networkView, nodesToViews, otherNode, partition, nodesSeenMap, edgesSeenMap);
			}
		}
	}
}

package org.cytoscape.command.util;

/*
 * #%L
 * Cytoscape Command Executor API (command-executor-api)
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

import java.util.List;

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;

/**
 * This class implements a wrapper for a List of CyEdges that
 * can be used by the Tunables mechanism.  Currently, it is only
 * implemented by command tunables so it will be ignored in
 * GUI (Swing) contexts.
 *
 * A typical use might look like:
 * <pre>
 * public EdgeList edgeList = new EdgeList(null);
 * 
 * &#64;Tunable(description="Edges to select", context="nogui")
 * public EdgeList getedgeList() {
 * 	edgeList.setNetwork(network); // This assumes the network is 
 * 	                              // made available via some other mechanism
 *	return edgeList;
 * }
 * 
 * public void setedgeList(EdgeList setValue) {}
 * </pre>
 *
 * @CyAPI.Final.Class
 * @CyAPI.InModule command-executor-api
 */
public class EdgeList {
	private CyNetwork network;
	private List<CyEdge> edgelist;

	/**
 	 * Empty constructor for an EdgeList.  Use this when the
 	 * target network won't be known until the Tunable is used.
 	 */
	public EdgeList() {
		network = null;
		edgelist = null;
	}

	/**
 	 * Constructor for EdgeList when the network is known
 	 * at time of creation.
 	 *
 	 * @param targetNetwork the network for the edges to be returned
 	 */
	public EdgeList(CyNetwork targetNetwork) {
		this.network = targetNetwork;
		edgelist = null;
	}

	/**
 	 * Set the network to be used by this EdgeList
 	 *
 	 * @param network the network to use for finding the edges
 	 */
	public synchronized void setNetwork(CyNetwork network) {
		this.network = network;
	}

	/**
 	 * Return the network used by this EdgeList
 	 *
 	 * @return the network to use for finding the edges
 	 */
	public CyNetwork getNetwork() {
		return this.network;
	}

	/**
 	 * Set the edgeList for this tunable
 	 *
 	 * @param edgeList the list of edges
 	 */
	public synchronized void setValue(List<CyEdge> edgeList) {
		this.edgelist = edgeList;
	}

	/**
 	 * Return the edgeList for this tunable
 	 *
 	 * @return the list of edges
 	 */
	public List<CyEdge> getValue() {
		return edgelist;
	}

}

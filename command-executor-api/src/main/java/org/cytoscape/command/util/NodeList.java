package org.cytoscape.command.util;

/*
 * #%L
 * Cytoscape Command Executor API (command-executor-api)
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

import java.util.List;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

/**
 * This class implements a wrapper for a List of CyNodes that
 * can be used by the Tunables mechanism.  Currently, it is only
 * implemented by command tunables so it will be ignored in
 * GUI (Swing) contexts.
 *
 * A typical use might look like:
 * <pre>
 * &#64;Tunable(description="Network", context="nogui", gravity=1.0)
 * CyNetwork network;
 *
 * public NodeList nodeList = new NodeList(null);
 * 
 * &#64;Tunable(description="Nodes to select", context="nogui", gravity=2.0)
 * public NodeList getnodeList() {
 * 	nodeList.setNetwork(network); // Should check for null
 *	return nodeList;
 * }
 * 
 * public void setnodeList(NodeList setValue) {}
 * </pre>
 *
 * @CyAPI.Final.Class
 * @CyAPI.InModule command-executor-api
 */
public class NodeList {
	private CyNetwork network;
	private List<CyNode> nodelist;

	/**
 	 * Create a new NodeList object with no initial network
 	 */
	public NodeList() {
		network = null;
		nodelist = null;
	}

	/**
 	 * Create a new NodeList object with an initial network
 	 *
 	 * @param targetNetwork the network this nodelist will be in
 	 */
	public NodeList(CyNetwork targetNetwork) {
		this.network = targetNetwork;
		nodelist = null;
	}

	/**
 	 * Set the network for this nodelist
 	 *
 	 * @param network the network for this nodelist
 	 */
	public synchronized void setNetwork(CyNetwork network) {
		this.network = network;
	}

	/**
 	 * Get the network for this nodelist
 	 *
 	 * @return the network we're looking through
 	 */
	public CyNetwork getNetwork() {
		return this.network;
	}

	/**
 	 * Set the list of nodes
 	 *
 	 * @param nodeList the list of nodes
 	 */
	public synchronized void setValue(List<CyNode> nodeList) {
		this.nodelist = nodeList;
	}

	/**
 	 * Get the list of nodes
 	 *
 	 * @return the list of nodes
 	 */
	public List<CyNode> getValue() {
		return nodelist;
	}

}

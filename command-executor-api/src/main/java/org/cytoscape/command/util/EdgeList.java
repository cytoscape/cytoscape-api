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

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;

/**
 * This class implements a wrapper for a List of CyEdges that
 * can be used by the Tunables mechanism.  Currently, it is only
 * implemented by command tunables so it will be ignored in
 * GUI (Swing) contexts.
 *
 * @CyAPI.Api.Final.Class
 * @CyAPI.InModule command-executor-api
 */
public class EdgeList {
	private CyNetwork network;
	private List<CyEdge> edgelist;

	public EdgeList() {
		network = null;
		edgelist = null;
	}

	public EdgeList(CyNetwork targetNetwork) {
		this.network = targetNetwork;
		edgelist = null;
	}

	public synchronized void setNetwork(CyNetwork network) {
		this.network = network;
	}

	public CyNetwork getNetwork() {
		return this.network;
	}

	public synchronized void setValue(List<CyEdge> nodeList) {
		this.edgelist = nodeList;
	}

	public List<CyEdge> getValue() {
		return edgelist;
	}

}

package org.cytoscape.command;

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
import org.cytoscape.model.CyNode;

import org.cytoscape.view.model.CyNetworkView;

/**
 * The service implementing StringToModel provides a common mechanism for converting
 * command arguments into Cytoscape model elements.  For example, the command:
 *
 * 	nodes select network=current nodelist="name:gene1,name:gene2"
 *
 * could be handled as follows:
 *
 * <pre>
 *     @Tunable(description="Network to select", context="nongui")
 *     String network;
 *     @Tunable(description="List of nodes to select", context="nongui")
 *     String nodelist;
 *
 *     SelectedNodesTask() {}
 *     public run(TaskMonitor mon) {
 *         CyNetwork net = stringToModel.getNetwork(network);
 *         List<CyNode> nodeList = stringToModel.getNodeList(net, nodelist);
 *         // Actually select the nodes...
 *     }
 * </pre>
 *
 *
 * @CyAPI.Api.Interface
 * @CyAPI.InModule command-executor-api
 */
public interface StringToModel {

	/**
 	 * Convert a string request for a network into a {@link CyNetwork}.  This method
 	 * may be used to find a network with a given name or the current network.
 	 * Future implementations might accept other columns to search on.
 	 *
 	 * @param strNet the name of the network to return or "current"
 	 * @return the returned CyNetwork or null if the name doesn't exist
 	 */
	public CyNetwork getNetwork(String strNet);

	/**
 	 * Convert a string request for a nodelist into a list of {@link CyNode}s.  This
 	 * method may be used to find selected nodes, or nodes matching a particular column
 	 * by using the pattern "column:value".  If no column is specified, it is assumed
 	 * that the column is the NAME column.  No wildcarding or regular expression matching
 	 * is supported at this time.  The following special keywords are also recognized:
 	 * 	all         return all nodes in this network
 	 * 	selected    return all selected nodes in this network
 	 * 	unselected  return all unselected nodes in this network
 	 *
 	 * @param net the CyNetwork to search through.  If null, use the current network
 	 * @param nodelist the string representing the nodelist
 	 * @return a list of CyNodes or null if nothing matched
 	 */
	public List<CyNode> getNodeList(CyNetwork net, String nodelist);

	/**
 	 * Convert a string request for a edgelist into a list of {@link CyEdge}s.  This
 	 * method may be used to find selected edges, or edges matching a particular column
 	 * by using the pattern "column:value".  If no column is specified, it is assumed
 	 * that the column is the NAME column.  No wildcarding or regular expression matching
 	 * is supported at this time.  The following special keywords are also recognized:
 	 * 	all         return all edges in this network
 	 * 	selected    return all selected edges in this network
 	 * 	unselected  return all unselected edges in this network
 	 *
 	 * @param net the CyNetwork to search through.  If null, use the current network
 	 * @param edgelist the string representing the edgelist
 	 * @return a list of CyEdges or null if nothing matched
 	 */
	public List<CyEdge> getEdgeList(CyNetwork net, String edgelist);
}

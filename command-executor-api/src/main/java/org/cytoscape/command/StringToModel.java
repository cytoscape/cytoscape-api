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
import org.cytoscape.model.CyRow;
import org.cytoscape.model.CyTable;

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

	static final String _CY_NETWORK_DESC = "Specifies a network by name, or by SUID if the prefix ```SUID:``` is used. The keyword ```CURRENT```, or a blank value can also be used to specify the current network.";
	
	static final String _CY_LIST_DESC = "A list of ```COLUMN:VALUE``` pairs of the format ```COLUMN1:VALUE1,COLUMN2:VALUE2,...``` can be used to match multiple values.";
	
	static final String _CY_ROW_DESC = "The pattern ```COLUMN:VALUE``` sets this parameter to any rows that contain the specified column value; if the ```COLUMN``` prefix is not used, the NAME column is matched by default.";
	
	public static final String CY_NETWORK_LONG_DESCRIPTION = "S" + _CY_NETWORK_DESC;
	
	public static final String CY_NETWORK_EXAMPLE_STRING = "current";
	/**
 	 * Convert a string request for a network into a {@link CyNetwork}.  This method
 	 * may be used to find a network with a given name or the current network.
 	 * Future implementations might accept other columns to search on.
 	 *
 	 * @param strNet the name of the network to return or "current"
 	 * @return the returned CyNetwork or null if the name doesn't exist
 	 */
	public CyNetwork getNetwork(String strNet);
	
	public static final String CY_TABLE_LONG_DESCRIPTION = "Specifies a table using the pattern ```TYPE:NETWORK``` where ```TYPE``` is one of ```node```, ```edge```, or ```network```. ```NETWORK``` s" + _CY_NETWORK_DESC;
	
	/**
 	 * Convert a string request for a table into a {@link CyTable}.  This method
 	 * may be used to find a table with a given name or the current table.
 	 * Future implementations might accept other columns to search on.
 	 *
 	 * @param strTable the name of the table to return or "current"
 	 * @return the returned CyTable or null if the name doesn't exist
 	 */
	public CyTable getTable(String strTable);

	public static final String CY_NODE_LIST_LONG_DESCRIPTION = "Specifies a list of nodes. The keywords ```all```, ```selected```, or ```unselected``` can be used to specify nodes by their selection state. " + _CY_ROW_DESC + " " + _CY_LIST_DESC;

	public static final String CY_NODE_LIST_EXAMPLE_STRING = "selected";
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

	public static final String CY_EDGE_LIST_LONG_DESCRIPTION = "Specifies a list of edges. The keywords ```all```, ```selected```, or ```unselected``` can be used to specify edges by their selection state. " + _CY_ROW_DESC + " " + _CY_LIST_DESC;

	public static final String CY_EDGE_LIST_EXAMPLE_STRING = "selected";
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

	public static final String CY_ROW_LIST_LONG_DESCRIPTION = "Specifies a list of rows. " + _CY_ROW_DESC + " " + _CY_LIST_DESC + " This parameter can also be set to ```all``` to include all rows.";
	/**
 	 * Convert a string request for a rowlist into a list of {@link CyRow}s.  This
 	 * method may be used to find all rows, or rows matching a particular column
 	 * by using the pattern "column:value".  If no column is specified, it is assumed
 	 * that the column is the NAME column.  No wildcarding or regular expression matching
 	 * is supported at this time.  The following special keywords are also recognized:
 	 * 	all         return all edges in this network
 	 *
 	 * @param table the CyTable to search through.
 	 * @param rowlist the string representing the rowlist
 	 * @return a list of CyRows or null if nothing matched
 	 */
	public List<CyRow> getRowList(CyTable table, String rowlist);
}

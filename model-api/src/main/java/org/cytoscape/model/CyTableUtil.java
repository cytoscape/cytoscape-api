package org.cytoscape.model;

/*
 * #%L
 * Cytoscape Model API (model-api)
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


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/** 
 * A collection of state-less, static utility methods provided to
 * simplify querying of table objects.
 * @CyAPI.Static.Class
 * @CyAPI.InModule model-api
 */
public class CyTableUtil {
	private CyTableUtil() { }

	/**
	 * A utility method that returns a list of nodes that have a boolean attribute
	 * in the {@link CyNetwork#DEFAULT_ATTRS} namespace specified by columnName and are in 
	 * the specified state.  If the attribute doesn't exist or is not of type 
	 * Boolean an IllegalArgumentException will be thrown.
	 * @param net The network to be queried.
	 * @param columnName The name of the column to be tested.
	 * @param state The state being queried. 
	 * @return a list of nodes that have a boolean attribute in the 
	 * {@link CyNetwork#DEFAULT_ATTRS} namespace specified by columnName and are in 
	 * the specified state. 
	 */
	public static List<CyNode> getNodesInState(final CyNetwork net, final String columnName, final boolean state) {
		if ( net == null )
			throw new NullPointerException("network is null");
		Collection<Long> suids = net.getDefaultNodeTable().getMatchingKeys(columnName, state, Long.class);
		List<CyNode> ret = new ArrayList<>(suids.size());
		for (Long suid : suids) {
			CyNode node = net.getNode(suid);
			if (node != null) {
				ret.add(node);
			}
		}
		return ret;
	}
	
	/**
	 * Equivalent to 
	 * <pre>
	 * getNodesInState(network, CyNetwork.SELECTED, true);
	 * </pre>
	 * @param network The network to be queried.
	 * @return a list of nodes that are in the selected state
	 */
	public static List<CyNode> getSelectedNodes(CyNetwork network) {
		return getNodesInState(network, CyNetwork.SELECTED, true);
	}

	/**
	 * A utility method that returns a list of edges that have a boolean attribute
	 * in the {@link CyNetwork#DEFAULT_ATTRS} namespace specified by columnName and are in 
	 * the specified state.  If the attribute doesn't exist or is not of type 
	 * Boolean an IllegalArgumentException will be thrown.
	 * @param net The network to be queried.
	 * @param columnName The name of the column to be tested.
	 * @param state The state being queried. 
	 * @return a list of edges that have a boolean attribute in the 
	 * {@link CyNetwork#DEFAULT_ATTRS} namespace specified by columnName and are in 
	 * the specified state. 
	 */
	public static List<CyEdge> getEdgesInState(final CyNetwork net, final String columnName, final boolean state) {
		if ( net == null )
			throw new NullPointerException("network is null");
		
		Collection<Long> suids = net.getDefaultEdgeTable().getMatchingKeys(columnName, state, Long.class);
		List<CyEdge> ret = new ArrayList<CyEdge>(suids.size());
		for (Long suid : suids) {
			CyEdge edge = net.getEdge(suid);
			if (edge != null) {
				ret.add(edge);
			}
		}
		return ret;
	}
	
	/**
	 * Equivalent to 
	 * <pre>
	 * getEdgesInState(network, CyNetwork.SELECTED, true);
	 * </pre>
	 * @param network The network to be queried.
	 * @return a list of edges that are in the selected state
	 */
	public static List<CyEdge> getSelectedEdges(CyNetwork network) {
		return getEdgesInState(network, CyNetwork.SELECTED, true);
	}

	
	/** Returns all the column names of a given table.
	 *  @param table  the table whose column names we want
	 *  @return the column names for all the columns in "table"
	 */
	public static Set<String> getColumnNames(final CyTable table) {
		final Set<String> names = new HashSet<String>();
		for (final CyColumn column : table.getColumns())
			names.add(column.getName());

		return names;
	}
}

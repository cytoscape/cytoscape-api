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
		List<CyNode> ret = new ArrayList<CyNode>();
		Collection<CyRow> rows = net.getDefaultNodeTable().getMatchingRows(columnName, state);
		for (CyRow row : rows) {
			CyNode node = net.getNode(row.get(CyTable.SUID, Long.class));
			if (node != null)
				ret.add(node);
		}
		return ret;
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
		List<CyEdge> ret = new ArrayList<CyEdge>();
		Collection<CyRow> rows = net.getDefaultEdgeTable().getMatchingRows(columnName, state);
		for (CyRow row : rows) {
			CyEdge edge = net.getEdge(row.get(CyTable.SUID, Long.class));
			if (edge != null)
				ret.add(edge);
		}
		return ret;
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

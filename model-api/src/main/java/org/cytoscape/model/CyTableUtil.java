/*
 Copyright (c) 2008, 2010, The Cytoscape Consortium (www.cytoscape.org)

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
package org.cytoscape.model;


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

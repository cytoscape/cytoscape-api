package org.cytoscape.model;

/*
 * #%L
 * Cytoscape Model API (model-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
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

import java.util.Map;
import java.util.Set;

/**
 * <p>Provides access to all network-table relationships.  <code>CyTable</code>s
 * can be associated with a <code>CyNetwork</code> by being assigned a
 * <em>type</em> and <em>namespace</em> within that
 * <code>CyNetwork</code>.  For example, a <code>CyTable</code> that stores
 * <code>CyNode</code> attributes would be of type <code>CyNode.class</code>.
 * You can associate multiple <code>CyTables</code> of a particular type
 * to a <code>CyNetwork</code> as long as each of those <code>CyTable</code>s
 * has a different namespace.  For instance, to add multiple node tables to a
 * <code>CyNetwork</code>, you would call:</p>
 * 
 * <pre>
 * setTable(network, CyNode.class, "my namespace", nodeTable1);
 * setTable(network, CyNode.class, "my other namespace", nodeTable2);
 * </pre>  
 * 
 * <p>So the triple (<code>CyNetwork</code>, type, namespace) refers to exactly
 * one table at any given time.  It's also possible to associate the same
 * <code>CyTable</code> with multiple (<code>CyNetwork</code>, type, namespace)
 * combinations.</p>
 * 
 * <p>By default, <code>CyNetwork</code>s are associated with network, node, and edge tables
 * for each of the following namespaces:</p>
 * 
 * <ul>
 *   <li>CyNetwork.DEFAULT_ATTRS</li>
 *   <li>CyNetwork.LOCAL_ATTRS</li>
 *   <li>CyNetwork.HIDDEN_ATTRS</li>
 * </ul>
 * 
 * @CyAPI.Api.Interface
 * @CyAPI.InModule model-api
 */
public interface CyNetworkTableManager {
	/**
	 * Associates the given table to the network using the specified namespace and type.
	 * @param network the {@link CyNetwork} to associate the given table to.
	 * @param type Type of {@link CyIdentifiable} associated with the table.
	 * @param namespace the {@link CyNetworkTableManager namespace} the table should belong to.
	 * @param table the table to associate to the network with the specified namespace and type.
	 */
	void setTable(CyNetwork network, Class<? extends CyIdentifiable> type, String namespace, CyTable table);
	
	/**
	 * Returns the table with the specified namespace and type from the
	 * network.
	 * @param network the network to check for the table.
	 * @param type Type of {@link CyIdentifiable} associated with the table.
	 * @param namespace the {@link CyNetworkTableManager namespace} the table should belong to.
	 * @return the table with the specified namespace and type from the
	 * network.
	 */
	CyTable getTable(CyNetwork network, Class<? extends CyIdentifiable> type, String namespace);

	/**
 	 * Returns the type of this table (either {@link CyNetwork}.class, {@link CyNode}.class, or
 	 * {@link CyEdge}.class.  If this table is not registered with the CyNetworkTableManager,
 	 * returns null;
 	 *
 	 * @param table the table we're inquiring about
 	 * @return the type of the table or null if the table is not registered
 	 */
	Class<? extends CyIdentifiable> getTableType(CyTable table);

	/**
 	 * Returns the namespace of this table.  If this table is not registered with the CyNetworkTableManager,
 	 * returns null;
 	 *
 	 * @param table the table we're inquiring about
 	 * @return the namespace of the table or null if the table is not registered
 	 */
	String getTableNamespace(CyTable table);

	/**
	 * Removes the table with the specified namespace and type from
	 * the network.
	 * @param network the network to remove the table from.
	 * @param type Type of {@link CyIdentifiable} associated with the table.
	 * @param namespace the {@link CyNetworkTableManager namespace} the table should belong to.
	 */
	void removeTable(CyNetwork network, Class<? extends CyIdentifiable> type, String namespace);
	
	/**
	 * Returns a read-only map of all of the tables for the specified type
	 * from the network.  Each table is keyed by its namespace.
	 * @param network The network to return the tables of.
	 * @param type The type of the tables to return.
	 * @return a read-only map of all the tables for the specified type 
	 * from the network.
	 */
	Map<String, CyTable> getTables(CyNetwork network, Class<? extends CyIdentifiable> type);
	
	/**
	 * Clears all the network-table relationships.
	 */
	void reset();

	/**
	 * Returns a read-only set of all networks managed by this instance.
	 * @return a read-only set of all networks managed by this instance. 
	 */
	Set<CyNetwork> getNetworkSet();

	/**
	 * Removes all tables associated with the given network.
	 * @param network the network whose tables should be removed.
	 */
	void removeAllTables(CyNetwork network);
}

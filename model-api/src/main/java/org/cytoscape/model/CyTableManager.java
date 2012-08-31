/*
 Copyright (c) 2010, The Cytoscape Consortium (www.cytoscape.org)

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


import java.util.Set;


/** 
 * A singleton object for managing registered {@link CyTable}s.
 *
 * <p>
 * When a {@link CyTable} is created through a {@link CyTableFactory},
 * it exists independently from the rest of Cytoscape.
 * However, when a {@code CyTable} is registered through the {@code CyTableManager},
 * {@code CyTableManager} does the following things for the {@code CyTable}:
 * <ul>
 *  <li>
 *   It fires the {@link org.cytoscape.model.events.TableAddedEvent}
 *   to inform other parts of Cytoscape that the {@code CyTable} was added
 *   to the {@code CyTableManager}.
 *  </li>
 *  <li>
 *   It also makes the {@code CyTable} available to other parts of
 *   Cytoscape through methods like {@link #getAllTables}.
 *  </li>
 *  <li>
 *   Because registering the {@code CyTable} notifies Cytoscape about
 *   the {@code CyTable}, Cytoscape will ensure that the {@code CyTable}
 *   is saved (see {@link CyTable#setSavePolicy}). If the {@code CyTable}
 *   is not registered with the {@code CyTableManager}, it will not
 *   be saved according to its {@link SavePolicy}.
 *  </li>
 * </ul>
 * </p>
 *
 * <p>
 * This interface, unfortunately, uses <i>very</i> confusing method names. Here is an explanation.
 * <ul>
 * <li>
 * <i>Associated</i> {@code CyTable}s are explicitly mapped to 
 * {@link CyNetwork}, {@link CyNode}s, or {@link CyEdge}s through the
 * {@link CyNetworkTableManager}.
 * Associated {@code CyTable}s are obtained through {@link #getLocalTables}.
 * </li>
 * <li>
 * <i>Unassociated</i> {@code CyTable}s are <i>not</i> explicitly mapped to 
 * network objects. Note that columns in an unassociated table can still be "mapped"
 * to network objects when an associated table has a virtual column
 * from an unassociated table.
 * Unassigned tables are obtained from {@link #getGlobalTables}.
 * </li>
 * </ul>
 * </p>
 *
 * @CyAPI.Api.Interface
 */
public interface CyTableManager {

	/**
	 * Returns a Set of all tables with the specified visibility.
	 * @param includePrivate Whether to include private CyTables
	 * in the list (i.e. all possible CyTables) or not.
	 * @return A Set containing CyTable SUIDs either
	 * including private CyTables (i.e. meaning all possible
	 * CyTables) or just public CyTables.
	 */
	Set<CyTable> getAllTables(boolean includePrivate);

	/** Registers a new table with the manager and fires a TableAddedEvent event.
	 *  @param table a non-null CyTable that will be added to the manager
	 */
	void addTable(CyTable table);

	/**
	 * Returns the table with the specified SUID. 
	 * @param suid The SUID identifying the CyTable.
	 * @return The CyTable identified by the suid. Will return null if a CyTable doesn't
	 *         exist for the  specified SUID.
	 */
	CyTable getTable(long suid);

	/** Deletes a mutable table.
	 *  @param suid  the SUID identifying the CyTable to be deleted
	 *  @throws IllegalArgumentException  if the table that we requested to be deleted is immutable
	 *          or if any of its columns are virtual columns in other tables
	 */
	void deleteTable(long suid);

	/**
	 * Releases all currently held references and resources.
	 */
	void reset();
	
	/**
	 * Returns a set of all global tables.
     * "Global tables" are tables that are not explicitly mapped to
     * {@link CyNetwork}s, {@link CyNode}s, and {@link CyEdge}s.
     * through {@link CyNetworkTableManager}.
     *
	 * @return All registered global tables
	 */
	Set<CyTable> getGlobalTables();
	
	/**
	 * Returns set of all local tables for the given data type.
     * "Local tables" are tables that are explicitly mapped to {@link CyNetwork}s,
     * {@link CyNode}s, and {@link CyEdge}s.
     * This method has <i>nothing</i> to do with {@link CyNetwork#LOCAL_ATTRS}.
	 * 
	 * @param type Type of the network object, i.e., {@code CyNetwork.class},
     * {@code CyNode.class}, or {@code CyEdge.class}.
	 * 
	 * @return Set of all registered tables associated with the given data type.
	 */
	Set<CyTable> getLocalTables(Class<? extends CyIdentifiable> type);
}

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
 * A singleton object that provides access to the available
 * tables in the system. Should be provided as an OSGi service.
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
	 * @return All registered global tables
	 */
	Set<CyTable> getGlobalTables();
	
	/**
	 * Returns set of all local tabses for the given data type.
	 * 
	 * @param type Type of the graph object, i.e., node, edge or network.
	 * 
	 * @return Set of all registered tables associated with the given data type.
	 */
	Set<CyTable> getLocalTables(Class<? extends CyIdentifiable> type);
}

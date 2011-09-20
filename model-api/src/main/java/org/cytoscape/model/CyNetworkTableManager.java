package org.cytoscape.model;

import java.util.Map;

/**
 * Provides access to all network-table relationships.
 */
public interface CyNetworkTableManager {
	/**
	 * Associates the given table to the network using the specified namespace and type.
	 */
	void setTable(CyNetwork network, Class<? extends CyTableEntry> type, String namespace, CyTable table);
	
	/**
	 * Returns the table with the specified namespace and type from the
	 * network.
	 */
	CyTable getTable(CyNetwork network, Class<? extends CyTableEntry> type, String namespace);
	
	/**
	 * Removes the table with the specified namespace and type from
	 * the network.
	 */
	void removeTable(CyNetwork network, Class<? extends CyTableEntry> type, String namespace);
	
	/**
	 * Returns a read-only map of all of the tables for the specified type
	 * from the network.  Each table is keyed by its namespace.
	 */
	Map<String, CyTable> getTables(CyNetwork network, Class<? extends CyTableEntry> type);
	
	/**
	 * Clears all the network-table relationships.
	 */
	void reset();
}

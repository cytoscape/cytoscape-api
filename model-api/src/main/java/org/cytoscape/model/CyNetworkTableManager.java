package org.cytoscape.model;

import java.util.Map;
import java.util.Set;

/**
 * Provides access to all network-table relationships.
 * @CyAPI.Api.Interface
 */
public interface CyNetworkTableManager {
	/**
	 * Associates the given table to the network using the specified namespace and type.
	 * @param network the {@link CyNetwork} to associate the given table to.
	 * @param type Type of {@link CyIdentifiable} associated with the table.
	 * @param namespace The name of the table relative to the network.
	 * @param table the table to associate to the network with the specified namespace and type.
	 */
	void setTable(CyNetwork network, Class<? extends CyIdentifiable> type, String namespace, CyTable table);
	
	/**
	 * Returns the table with the specified namespace and type from the
	 * network.
	 * @param network the network to check for the table.
	 * @param type Type of {@link CyIdentifiable} associated with the table.
	 * @param namespace The name of the table relative to the network.
	 * @return the table with the specified namespace and type from the
	 * network.
	 */
	CyTable getTable(CyNetwork network, Class<? extends CyIdentifiable> type, String namespace);
	
	/**
	 * Removes the table with the specified namespace and type from
	 * the network.
	 * @param network the network to remove the table from.
	 * @param type Type of {@link CyIdentifiable} associated with the table.
	 * @param namespace The name of the table relative to the network.
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
}

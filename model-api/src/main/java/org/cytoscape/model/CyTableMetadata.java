package org.cytoscape.model;

import java.util.Set;

/**
 * A snapshot of information about the relationships shared between a CyTable
 * instance and any associated CyNetworks.
 */
public interface CyTableMetadata {
	/**
	 * Returns the type of the data associated with this object's table.
	 * This is typically either <code>CyNetwork.class</code>,
	 * <code>CyNode.class</code>, or <code>CyEdge.class</code>.
	 * @return the type of the data associated with this object's table.
	 */
	Class<?> getType();
	
	/**
	 * Returns the table whose metadata is described by this instance.
	 * @return the table whose metadata is described by this instance.
	 */
	CyTable getCyTable();
	
	/**
	 * Returns all the networks associated with this object's table.
	 * @return all the networks associated with this object's table.
	 */
	Set<CyNetwork> getCyNetworks();
	
	/**
	 * Returns the namespace used as the key to this object's table in
	 * {@link CyTableManager.getTableMap()}. 
	 * @return
	 */
	String getNamespace();
}

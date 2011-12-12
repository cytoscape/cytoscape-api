package org.cytoscape.model;

/**
 * A snapshot of information about a relationship shared between a CyTable
 * instance and an associated CyNetworks.
 * @CyAPI.Api.Interface
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
	CyTable getTable();
	
	/**
	 * Returns all the networks associated with this object's table.
	 * @return all the networks associated with this object's table.
	 */
	CyNetwork getNetwork();
	
	/**
	 * Returns the namespace used as the key to this object's table in
	 * {@link CyNetworkTableManager#getTables}. 
	 * @return the namespace used as the key to this object's table in
	 * {@link CyNetworkTableManager#getTables}. 
	 */
	String getNamespace();
}

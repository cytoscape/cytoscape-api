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

/**
 * A snapshot of information about a relationship shared between a CyTable
 * instance and an associated CyNetworks. The interface is used to capture
 * table information for serialization and shouldn't be needed for most
 * normal use of tables.
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

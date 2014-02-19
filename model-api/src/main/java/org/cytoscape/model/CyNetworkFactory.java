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


/**
 * A singleton factory object used for instantiating CyNetwork 
 * objects. The CyNetworkFactory should be available as an
 * OSGi service.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule model-api
 */
public interface CyNetworkFactory {
	/**
	 * Returns a new, empty {@link CyNetwork} object.
	 * The new network's save policy is {@link SavePolicy#SESSION_FILE} by default.
	 * If you want to create a network that should not be saved in session files, use
	 * the {@link #createNetwork(SavePolicy)} method instead, and set the save policy to {@link SavePolicy#DO_NOT_SAVE}.
	 * @return A new, empty {@link CyNetwork} object. 
	 */
	CyNetwork createNetwork();
	
	/**
	 * Returns a new, empty {@link CyNetwork} object.
	 * @param policy the save policy to follow during the life-cycle of the CyNetwork.
	 * @return A new, empty {@link CyNetwork} object. 
	 */
	CyNetwork createNetwork(SavePolicy policy);

	/**
	 * Returns a new, empty {@link CyNetwork} object where the associated default tables are private. 
	 * This method should only be used in special cases where the network created is not intended to
	 * be used or shared like a normal network within the system.
	 * The new network's save policy is {@link SavePolicy#SESSION_FILE} by default.
	 * If you want to create a network that should not be saved in session files, use
	 * the {@link #createNetwork(SavePolicy)} method instead, and set the save policy to {@link SavePolicy#DO_NOT_SAVE}.
	 * @return A new, empty {@link CyNetwork} object. 
	 */
	CyNetwork createNetworkWithPrivateTables();
	
	/**
	 * Returns a new, empty {@link CyNetwork} object where the associated default tables are private. 
	 * This method should only be used in special cases where the network created is not intended to
	 * be used or shared like a normal network within the system.
	 * @param policy the save policy to follow during the life-cycle of the CyNetwork.
	 * @return A new, empty {@link CyNetwork} object. 
	 */
	CyNetwork createNetworkWithPrivateTables(SavePolicy policy);
}

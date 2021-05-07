package org.cytoscape.model;

/*
 * #%L
 * Cytoscape Model API (model-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2021 The Cytoscape Consortium
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


import java.util.Set;


/**
 * Basic access to networks and view in an instance of Cytoscape.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule model-api
 */
public interface CyNetworkManager {
	/**
	 * Provides the set of all the networks known to the network manager.
	 * 
	 * @return the complete set of all the currently known networks in Cytoscape
	 */
	public Set<CyNetwork> getNetworkSet();

	/**
	 * Returns the network corresponding to the provided ID
	 * 
	 * @param id  an ID for an existing network
	 * 
	 * @return null if "id" does not correspond to a network known to the network manager or the
	 *         actual network if "id" does correspond to a network
	 */
	public CyNetwork getNetwork(long id);

	/**
	 *  Tests whether a network has been registered with the network manager or not.
	 * 
	 * @param networkId  possibly the ID of a network
	 * 
	 * @return true if "networkId" does correspond to a network known to the network manager, else false
	 */
	public boolean networkExists(long networkId);

	/**
	 *  Destroys a network.
	 * 
	 * @param network  a non-null reference to a {@link CyNetwork}
	 */
	public void destroyNetwork(CyNetwork network);

	/**
	 * Registers a network with the network manager and sets the network as current.
	 * Does nothing if the network is already known to the network manager.
	 * This has the same effect as {@code addNetwork(network, true)};
	 * 
	 * @param network  a non-null {@link CyNetwork}
	 */
	public void addNetwork(final CyNetwork network);
	
	/**
	 * Registers a network with the network manager.
	 * Does nothing if the network is already known to the network manager.
	 * 
	 * @param network  a non-null {@link CyNetwork}
	 * @param setCurrent if true, also sets the new network as current
	 */
	public void addNetwork(final CyNetwork network, final boolean setCurrent);

	/** Releases all currently held references and resources. */
	public void reset();
}

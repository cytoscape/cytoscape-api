/*
 File: CyNetworkManager.java

 Copyright (c) 2006, 2010, The Cytoscape Consortium (www.cytoscape.org)

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

import org.cytoscape.model.CyNetwork;


/**
 * Basic access to networks and view in an instance of Cytoscape.
 * @CyAPI.Api.Interface
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
	 * @param network_id  possibly the ID of a network
	 * 
	 * @return true if "network_id" does correspond to a network known to the network manager, else false
	 */
	public boolean networkExists(long network_id);

	/**
	 *  Destroys a network.
	 * 
	 * @param network  a non-null reference to a {@link CyNetwork}
	 */
	public void destroyNetwork(CyNetwork network);

	/**
	 * Registers a network with the network manager.  Does nothing if the network is already
	 * known to the network manager.
	 * 
	 * @param network  a non-null {@link CyNetwork}
	 */
	public void addNetwork(final CyNetwork network);

	/** Releases all currently held references and resources. */
	public void reset();
}

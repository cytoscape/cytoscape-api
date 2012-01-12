/*
 File: CyNetworkViewManager.java

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
package org.cytoscape.view.model;


import java.util.Set;

import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.model.CyNetwork;


/**
 * Basic access to network views in an instance of Cytoscape.
 * @CyAPI.Api.Interface
 */
public interface CyNetworkViewManager {
	/**
	 * Provides the set of network views that are currently known to the network manager.
	 * 
	 * @return the set of all network views maintained by the network view manager
	 */
	public Set<CyNetworkView> getNetworkViewSet();

	/**
	 * Returns the network view corresponding to the specified network, if found.
	 * 
	 * @param network  The network we're requesting a view of.
	 * 
	 * @return null if no network view was found corresponding to "networkId", else the network view
	 */
	public CyNetworkView getNetworkView(CyNetwork network);

	/**
	 * Determines whether a network view for the specified network is known to the network view manager.
	 * 
	 * @param network  The network for which we want to know whether a view exists.
	 * 
	 * @return true if a view was found that corresponds to "networkId", else false
	 */
	public boolean viewExists(CyNetwork network);

	/**
	 * Destroys a network view.
	 * 
	 * @param view  a non-null network view
	 */
	public void destroyNetworkView(CyNetworkView view);

	/**
	 * Registers a network view with the network view manager.
	 * 
	 * @param view  a non-null network view
	 */
	public void addNetworkView(CyNetworkView view);

	/** Releases all currently held references and resources. */
	public void reset();
}

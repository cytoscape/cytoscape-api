package org.cytoscape.view.model;

/*
 * #%L
 * Cytoscape View Model API (viewmodel-api)
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

import java.util.Collection;
import java.util.Set;

import org.cytoscape.model.CyNetwork;

/**
 * Basic access to network views in an instance of Cytoscape.
 * 
 * @CyAPI.Api.Interface
 * @CyAPI.InModule viewmodel-api
 */
public interface CyNetworkViewManager {

	/**
	 * Provides the set of network views that are currently known to the network
	 * manager.
	 * 
	 * @return the set of all network views maintained by the network view manager
	 */
	Set<CyNetworkView> getNetworkViewSet();

	/**
	 * Returns a collection of network views corresponding to the specified network, if found.
	 * 
	 * @param network
	 *            The network we're requesting a view of.
	 * 
	 * @return Empty set if network view was found corresponding to "networkId",
	 *         else collection of network views
	 */
	Collection<CyNetworkView> getNetworkViews(final CyNetwork network);

	/**
	 * Determines whether a network view for the specified network is known to
	 * the network view manager.
	 * 
	 * @param network
	 *            The network for which we want to know whether a view exists.
	 * 
	 * @return true if a view was found that corresponds to "networkId", else
	 *         false
	 */
	boolean viewExists(final CyNetwork network);

	
	/**
	 * Destroys a network view.
	 * 
	 * @param view a non-null network view
	 */
	void destroyNetworkView(final CyNetworkView view);


	/**
	 * Registers a network view with the network view manager and sets the new view as current.
	 * This has the same effect as {@code addNetworkView(view, true)};
	 * 
	 * @param view a non-null network view
	 */
	void addNetworkView(final CyNetworkView view);
	
	/**
	 * Registers a network view with the network view manager.
	 * 
	 * @param view a non-null network view
	 * @param setCurrent if true, also sets the new view as current
	 */
	void addNetworkView(final CyNetworkView view, final boolean setCurrent);


	/**
	 * Releases all currently held references and resources.
	 * 
	 * Note: make sure all references are released after this method call.
	 * Otherwise, it can be a potential memory leak problem.
	 * 
	 * @CyAPI.NoReference.Method Apps should not call this method. Resetting 
	 * the CyNetworkViewManager at the wrong time will put Cytoscape into 
	 * an inconsistent internal state.
	 */
	void reset();
}
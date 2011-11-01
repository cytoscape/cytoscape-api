/*
 File: CyApplicationManager.java

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
package org.cytoscape.application;


import java.util.List;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyTable;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.presentation.RenderingEngine;


/**
 * Basic access to current and/or currently selected networks, 
 * views and rendering engines in an instance of Cytoscape.
 * @CyAPI.Api.Interface
 */
public interface CyApplicationManager {
	
	/**
	 * Provides access to the current network.
	 * @return the current network or null if there is no current network
	 */
	public CyNetwork getCurrentNetwork();

	/**
	 * Sets the current network to the one with the provided network SUID.
	 * @param network_id  must be the SUID of a network
	 */
	public void setCurrentNetwork(final long network_id);

	/**
	 * Returns the current network view.
	 * @return the current network view or null if no network 
	 * is currently being visualized
	 */
	public CyNetworkView getCurrentNetworkView();

	/**
	 * Sets the current network view based on the specified ID.
	 * @param view_id The <i>network</i> SUID of the network view to
	 * be made current.
	 */
	public void setCurrentNetworkView(final long view_id);

	/**
	 * Returns the list of selected networks.  
	 * @return The list of selected networks.
	 */
	public List<CyNetwork> getSelectedNetworks();

	/**
	 * Returns the list of selected network views.  
	 * @return The list of selected network views.
	 */
	public List<CyNetworkView> getSelectedNetworkViews();

	/**
	 * Sets the network views associated with the specified network 
	 * IDs as selected.
	 * @param modelIDs The <i>model</i> SUIDs of the networks that have 
	 * been selected.
	 */
	public void setSelectedNetworkViews(final List<Long> modelIDs);

	/**
	 * Sets the networks associated with the specified IDs as selected.
	 * @param ids The SUIDs of the networks that have been selected.
	 */
	public void setSelectedNetworks(final List<Long> ids);

	/**
	 * Returns the rendering engine associated with the current network view.
	 * @return The rendering engine associated with the current network view.
	 */
	public RenderingEngine<CyNetwork> getCurrentRenderingEngine();

	/**
	 * Sets the current rendering engine.
	 * @param engine The rendering engine that should be made current.
	 */
	public void setCurrentRenderingEngine(final RenderingEngine<CyNetwork> engine);

	/**
	 * Returns the currently active table.
	 * @return the currently active {@link CyTable}.
	 */
	public CyTable getCurrentTable();
	
	/**
	 * Sets the currently active table.
	 * @param table The table that should be made current.
	 */
	public void setCurrentTable(CyTable table);
	
	
}

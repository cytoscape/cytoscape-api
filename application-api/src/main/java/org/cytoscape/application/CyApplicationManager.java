package org.cytoscape.application;

/*
 * #%L
 * Cytoscape Application API (application-api)
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


import java.util.List;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyTable;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.presentation.RenderingEngine;


/**
 * Basic access to current and/or currently selected networks, 
 * views and rendering engines in an instance of Cytoscape.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule application-api
 */
public interface CyApplicationManager {
	
	/**
	 * Provides access to the current network.
	 * @return the current network or null if there is no current network
	 */
	public CyNetwork getCurrentNetwork();

	/**
	 * Sets the current network to the specified network.
	 * If the passed network is different from the current one, a {@link SetCurrentNetworkEvent} is fired.
	 * If the passed network is not yet selected, any selected networks are unselected before the passed
	 * one is selected. That means that the {@link SetSelectedNetworksEvent} can also be fired.
	 * @param net The network that will become the current network. 
	 */
	public void setCurrentNetwork(final CyNetwork net);

	/**
	 * Returns the current network view.
	 * @return the current network view or null if no network 
	 * is currently being visualized
	 */
	public CyNetworkView getCurrentNetworkView();

	/**
	 * Sets the current network view to the specified network view.
	 * @param netView The network view to become the current network view. 
	 */
	public void setCurrentNetworkView(final CyNetworkView netView);

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
	 * Sets the specified network views as selected. 
	 * @param views The list of network views to be selected.
	 */
	public void setSelectedNetworkViews(final List<CyNetworkView> views);

	/**
	 * Sets the networks specified as selected.
	 * @param nets The networks to be selected.
	 */
	public void setSelectedNetworks(final List<CyNetwork> nets);

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

	/** 
	 * Releases all currently held references and resources. 
	 */
	public void reset();
	
	/**
	 * Returns the NetworkViewRenderer associated with the current
	 * RenderingEngine.
	 * 
	 * @return the NetworkViewRenderer associated with the current
	 * RenderingEngine
	 */
	NetworkViewRenderer getCurrentNetworkViewRenderer();
	
	/**
	 * Returns the NetworkViewRenderer that should be used to create
	 * CyNetworkViews.
	 * @return the NetworkViewRenderer that should be used to create
	 * CyNetworkViews.
	 */
	NetworkViewRenderer getDefaultNetworkViewRenderer();
	
	/**
	 * Sets the NetworkViewRenderer that should be used to create
	 * CyNetworkViews.
	 * @param renderer the NetworkViewRenderer that should be used by
	 *                 default.
	 */
	void setDefaultNetworkViewRenderer(NetworkViewRenderer renderer);
}

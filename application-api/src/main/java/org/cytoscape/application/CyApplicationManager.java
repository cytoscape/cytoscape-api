package org.cytoscape.application;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyTable;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.presentation.RenderingEngine;

/*
 * #%L
 * Cytoscape Application API (application-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2019 The Cytoscape Consortium
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
	 * If the passed network is different from the current one, a 
	 * {@link org.cytoscape.application.events.SetCurrentNetworkEvent} is fired.
	 * If the passed network is not yet selected, any selected networks are unselected before the passed
	 * one is selected. That means that the {@link org.cytoscape.application.events.SetSelectedNetworksEvent} 
	 * can also be fired.
	 * @param net The network that will become the current network (it can be null). 
	 */
	public void setCurrentNetwork(final CyNetwork net);

	/**
	 * Returns the current network view.
	 * @return the current network view or null if no network is currently being visualized
	 */
	public CyNetworkView getCurrentNetworkView();

	/**
	 * Sets the current network view to the specified network view.
	 * @param netView The network view to become the current network view (it can be null). 
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
	 * @return the currently active {@link CyTable} or null if no table is currently being visualized
	 */
	public CyTable getCurrentTable();
	
	/**
	 * Sets the currently active table.
	 * @param table The table that should be made current (it can be null).
	 */
	public void setCurrentTable(CyTable table);

	/** 
	 * Releases all currently held references and resources. 
	 */
	public void reset();
	
	/**
	 * Returns the NetworkViewRenderer associated with the current RenderingEngine.
	 * @return the NetworkViewRenderer associated with the current RenderingEngine
	 */
	NetworkViewRenderer getCurrentNetworkViewRenderer();
	
	/**
	 * Returns the NetworkViewRenderer that should be used to create CyNetworkViews.
	 * @return the NetworkViewRenderer that should be used to create CyNetworkViews.
	 */
	NetworkViewRenderer getDefaultNetworkViewRenderer();
	
	/**
	 * Sets the {@link NetworkViewRenderer} that should be used to create CyNetworkViews.
	 * @param renderer the NetworkViewRenderer that should be used by default.
	 */
	void setDefaultNetworkViewRenderer(NetworkViewRenderer renderer);

	/**
	 * Returns the {@link NetworkViewRenderer} that has the passed id or null if it doesn't exist.
	 * @param rendererId
	 * @return the NetworkViewRenderer that should be used to create CyNetworkViews
	 */
	NetworkViewRenderer getNetworkViewRenderer(String rendererId);
	
	/**
	 * Returns a set with all the {@link NetworkViewRenderer} objects that have been registered.
	 * @return the complete set of all the currently known renderers
	 */
	Set<NetworkViewRenderer> getNetworkViewRendererSet();
	
	
	/**
	 * Returns the TableViewRenderer that should be used to create CyTableViews.
	 * @return the TableViewRenderer that should be used to create CyTableViews.
	 */
	TableViewRenderer getDefaultTableViewRenderer();
	
	/**
	 * Sets the {@link TableViewRenderer} that should be used to create CyTableViews.
	 * @param renderer the TableViewRenderer that should be used by default.
	 */
	void setDefaultTableViewRenderer(TableViewRenderer renderer);

	/**
	 * Returns the {@link TableViewRenderer} that has the passed id or null if it doesn't exist.
	 * @param rendererId
	 * @return the TableViewRenderer that should be used to create CyTableViews
	 */
	TableViewRenderer getTableViewRenderer(String rendererId);
	
	/**
	 * Returns a set with all the {@link TableViewRenderer} objects that have been registered.
	 * @return the complete set of all the currently known renderers
	 */
	Set<TableViewRenderer> getTableViewRendererSet();
	
	
	/**
	 * Get the current working directory.
	 * @return a File pointing to the current working directory as set in Properties,
	 * or user.dir if a value is not set (or if the set value is not a valid directory)
	 */
	File getCurrentDirectory();
	
	/**
	 * Set the current working directory.
	 * @param dir a File object pointing to an existing directory
	 * @return true if successful, false if the specified File is not a directory or does not exist
	 */
	boolean setCurrentDirectory(File dir);
}

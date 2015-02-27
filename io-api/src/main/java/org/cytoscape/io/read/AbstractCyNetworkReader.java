package org.cytoscape.io.read;

/*
 * #%L
 * Cytoscape IO Impl (io-impl)
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

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.NetworkViewRenderer;
import org.cytoscape.model.CyColumn;
import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNetworkFactory;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyTable;
import org.cytoscape.model.CyTableUtil;
import org.cytoscape.model.subnetwork.CyRootNetwork;
import org.cytoscape.model.subnetwork.CyRootNetworkManager;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.ProvidesTitle;
import org.cytoscape.work.Tunable;
import org.cytoscape.work.util.ListSingleSelection;

public abstract class AbstractCyNetworkReader extends AbstractTask implements CyNetworkReader {

	private static final String CREATE_NEW_COLLECTION_STRING = "Create new network collection";

	private final Map<String, CyRootNetwork> name2RootMap;
	private final Map<Object, CyNode> nodeMap;

	private ListSingleSelection<String> rootNetworkList;
	private ListSingleSelection<String> targetColumnList;
	private ListSingleSelection<NetworkViewRenderer> rendererList;

	/**
	 * Data stream for the networks to be created.
	 */
	protected InputStream inputStream;

	/**
	 * Array of networks to be returned.
	 */
	protected CyNetwork[] networks;

	/**
	 * Will be used for creating network views.
	 */
	protected final CyNetworkViewFactory cyNetworkViewFactory;

	/**
	 * Will be used to create new CySubNetwork if this reader needs to create new CyRootNetwork.
	 */
	protected final CyNetworkFactory cyNetworkFactory;
	
	protected CyApplicationManager cyApplicationManager;


	@ProvidesTitle
	public String getTitle() {
		return "Import Network";
	}
	
	@Tunable(description = "Network View Renderer:", gravity = 3.0)
	public ListSingleSelection<NetworkViewRenderer> getNetworkViewRendererList() {
		return rendererList;
	}
	
	public void setNetworkViewRendererList(final ListSingleSelection<NetworkViewRenderer> rendererList) {
		this.rendererList = rendererList;
	}

	private final ListSingleSelection<String> getTargetColumns(final CyNetwork network) {
		final CyTable selectedTable = network.getTable(CyNode.class,
				CyRootNetwork.SHARED_ATTRS);
		final List<String> colNames = new ArrayList<>();

		// Work-around to make the "shared name" the first in the list
		boolean containSharedName = false;
		// check if "shared name" column exist
		if (CyTableUtil.getColumnNames(selectedTable).contains(
				CyRootNetwork.SHARED_NAME)) {
			containSharedName = true;
			colNames.add(CyRootNetwork.SHARED_NAME);
		}

		for (final CyColumn col : selectedTable.getColumns()) {
			// Exclude SUID from the mapping key list
			if (col.getName().equalsIgnoreCase(CyIdentifiable.SUID)) {
				continue;
			}

			if (col.getName().equalsIgnoreCase(CyRootNetwork.SHARED_NAME)
					&& containSharedName) {
				// "shared name" is already added in the first
				continue;
			}
			colNames.add(col.getName());
		}

		return new ListSingleSelection<>(colNames);
	}

	@Tunable(description = "Node Identifier Mapping Column:", gravity = 2.0, listenForChange = { "RootNetworkList" })
	public ListSingleSelection<String> getTargetColumnList() {
		return targetColumnList;
	}

	public void setTargetColumnList(ListSingleSelection<String> colList) {
		this.targetColumnList = colList;
		// looks like this does not have any effect, is this a bug?
		this.targetColumnList.setSelectedValue(CyRootNetwork.SHARED_NAME);
	}

	@Tunable(description = "Network Collection:", gravity = 1.0)
	public ListSingleSelection<String> getRootNetworkList() {
		return rootNetworkList;
	}

	public void setRootNetworkList(final ListSingleSelection<String> roots) {
		if (rootNetworkList.getSelectedValue().equalsIgnoreCase(CREATE_NEW_COLLECTION_STRING)) {
			// set default
			List<String> colNames = new ArrayList<>();
			colNames.add(CyRootNetwork.SHARED_NAME);
			targetColumnList = new ListSingleSelection<>(colNames);
			
			return;
		}
		
		targetColumnList = getTargetColumns(name2RootMap.get(rootNetworkList.getSelectedValue()));
	}
	
	/**
	 * 
	 * @param inputStream
	 * @param cyApplicationManager
	 * @param cyNetworkFactory
	 * @param cyNetworkManager
	 * @param cyRootNetworkManager
	 */
	public AbstractCyNetworkReader(final InputStream inputStream,
								   final CyApplicationManager cyApplicationManager,
								   final CyNetworkFactory cyNetworkFactory,
								   final CyNetworkManager cyNetworkManager,
								   final CyRootNetworkManager cyRootNetworkManager) {
		this(inputStream, cyApplicationManager.getDefaultNetworkViewRenderer().getNetworkViewFactory(),
				cyNetworkFactory, cyNetworkManager, cyRootNetworkManager);
		this.cyApplicationManager = cyApplicationManager;
		
		init();
	}
	
	/**
	 * 
	 * 
	 * @param inputStream
	 * @param cyNetworkViewFactory
	 * @param cyNetworkFactory
	 * @param cyNetworkManager
	 * @param cyRootNetworkManager
	 */
	public AbstractCyNetworkReader(final InputStream inputStream,
								   final CyNetworkViewFactory cyNetworkViewFactory,
								   final CyNetworkFactory cyNetworkFactory,
								   final CyNetworkManager cyNetworkManager,
								   final CyRootNetworkManager cyRootNetworkManager) {
		if (inputStream == null)
			throw new NullPointerException("Input stream is null");
		if (cyNetworkViewFactory == null)
			throw new NullPointerException("CyNetworkViewFactory is null");
		if (cyNetworkFactory == null)
			throw new NullPointerException("CyNetworkFactory is null");
		if (cyNetworkManager == null)
			throw new NullPointerException("CyNetworkManager is null");
		if (cyRootNetworkManager == null)
			throw new NullPointerException("CyRootNetworkManager is null");

		this.inputStream = inputStream;
		this.cyNetworkViewFactory = cyNetworkViewFactory;
		this.cyNetworkFactory = cyNetworkFactory;
		this.name2RootMap = getRootNetworkMap(cyNetworkManager, cyRootNetworkManager);
		this.nodeMap = new HashMap<>(10000);
		
		init();
	}
	
	private void init() {
		// initialize the network Collection
		final List<String> rootNames = new ArrayList<>();
		rootNames.add(CREATE_NEW_COLLECTION_STRING);
		rootNames.addAll(name2RootMap.keySet());
		rootNetworkList = new ListSingleSelection<>(rootNames);
		rootNetworkList.setSelectedValue(rootNames.get(0));

		// initialize target attribute list
		final List<String> colNames = new ArrayList<>();
		colNames.add(CyRootNetwork.SHARED_NAME);
		targetColumnList = new ListSingleSelection<>(colNames);
		
		// initialize renderer list
		final List<NetworkViewRenderer> renderers = new ArrayList<>();
		
		if (cyApplicationManager != null) {
			final Set<NetworkViewRenderer> rendererSet = cyApplicationManager.getNetworkViewRendererSet();
			
			// If there is only one registered renderer, we don't want to add it to the List Selection,
			// so the combo-box does not appear to the user, since there is nothing to select anyway.
			if (rendererSet.size() > 1) {
				renderers.addAll(rendererSet);
				Collections.sort(renderers, new Comparator<NetworkViewRenderer>() {
					@Override
					public int compare(NetworkViewRenderer r1, NetworkViewRenderer r2) {
						return r1.toString().compareToIgnoreCase(r2.toString());
					}
				});
			}
		}
		
		rendererList = new ListSingleSelection<>(renderers);
	}
	
	@Override
	public CyNetwork[] getNetworks() {
		return networks;
	}

	/**
	 * Get target network collection, i.e., parent root network for all networks
	 * to be loaded.
	 * 
	 * @return Root network for this network collection. If there is no such root, returns null.
	 */
	protected final CyRootNetwork getRootNetwork() {
		final String networkCollectionName = this.rootNetworkList.getSelectedValue();
		final CyRootNetwork rootNetwork = this.name2RootMap.get(networkCollectionName);

		if (rootNetwork != null) {
			// Initialize the map of nodes only when we add network to existing collection.
			this.initNodeMap(rootNetwork);
		}
		
		return rootNetwork;
	}

	/**
	 * Returns map from key value to existing CyNode.
	 * 
	 * @return
	 */
	protected Map<Object, CyNode> getNodeMap() {
		return this.nodeMap;
	}
	
	protected CyNetworkViewFactory getNetworkViewFactory() {
		if (rendererList != null && rendererList.getSelectedValue() != null)
			return rendererList.getSelectedValue().getNetworkViewFactory();
		
		return cyNetworkViewFactory;
	}

	private final void initNodeMap(final CyRootNetwork rootNetwork) {
		final String keyColumnName = this.getTargetColumnList().getSelectedValue();
		final List<CyNode> nodes = rootNetwork.getNodeList();
		
		for (final CyNode node : nodes) {
			final Object keyValue = rootNetwork.getRow(node).getRaw(keyColumnName);
			
			if (keyValue != null)
				this.nodeMap.put(keyValue, node);
		}
	}

	private final Map<String, CyRootNetwork> getRootNetworkMap(CyNetworkManager cyNetworkManager,
			CyRootNetworkManager cyRootNetworkManager) {
		final Map<String, CyRootNetwork> name2RootMap = new HashMap<>();

		for (CyNetwork net : cyNetworkManager.getNetworkSet()) {
			final CyRootNetwork rootNet = cyRootNetworkManager.getRootNetwork(net);
			
			if (!name2RootMap.containsValue(rootNet))
				name2RootMap.put(rootNet.getRow(rootNet).get(CyRootNetwork.NAME, String.class), rootNet);
		}

		return name2RootMap;
	}

}
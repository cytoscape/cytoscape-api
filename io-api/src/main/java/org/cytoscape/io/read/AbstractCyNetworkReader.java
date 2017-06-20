package org.cytoscape.io.read;

import java.io.InputStream;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
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
import org.cytoscape.model.subnetwork.CyRootNetwork;
import org.cytoscape.model.subnetwork.CyRootNetworkManager;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.ProvidesTitle;
import org.cytoscape.work.Tunable;
import org.cytoscape.work.util.ListSingleSelection;

/*
 * #%L
 * Cytoscape IO API (io-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2017 The Cytoscape Consortium
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
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule io-api
 */
public abstract class AbstractCyNetworkReader extends AbstractTask implements CyNetworkReader {

	private static final String CREATE_NEW_COLLECTION_STRING = "-- Create new network collection --";

	private Map<String, CyRootNetwork> name2RootMap;
	private Map<Object, CyNode> nodeMap;

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
	
	private CyNetworkManager cyNetworkManager;
	private CyRootNetworkManager cyRootNetworkManager;


	@ProvidesTitle
	public String getTitle() {
		return "Import Network";
	}
	
	@Tunable(description = "Network Collection:", groups = "_Network", gravity = 1.0)
	public ListSingleSelection<String> getRootNetworkList() {
		return rootNetworkList;
	}

	public void setRootNetworkList(final ListSingleSelection<String> roots) {
		rootNetworkList = roots;
		final String rootNetName = rootNetworkList.getSelectedValue();

		if (rootNetName != null && !rootNetName.equalsIgnoreCase(CREATE_NEW_COLLECTION_STRING))
			setTargetColumnList(getTargetColumns(name2RootMap.get(rootNetName)));
		else
			setTargetColumnList(new ListSingleSelection<>());
	}
	
	@Tunable(description = "Node Identifier Mapping Column:", groups = "_Network", gravity = 2.0, listenForChange = { "RootNetworkList" })
	public ListSingleSelection<String> getTargetColumnList() {
		return targetColumnList;
	}

	public void setTargetColumnList(ListSingleSelection<String> colList) {
		targetColumnList = colList;
		
		if (targetColumnList.getPossibleValues().contains(CyRootNetwork.SHARED_NAME))
			targetColumnList.setSelectedValue(CyRootNetwork.SHARED_NAME);
	}
	
	@Tunable(description = "Network View Renderer:", groups = "_Network", gravity = 3.0)
	public ListSingleSelection<NetworkViewRenderer> getNetworkViewRendererList() {
		return rendererList;
	}
	
	public void setNetworkViewRendererList(final ListSingleSelection<NetworkViewRenderer> rendererList) {
		this.rendererList = rendererList;
	}
	
	public AbstractCyNetworkReader(final InputStream inputStream,
								   final CyApplicationManager cyApplicationManager,
								   final CyNetworkFactory cyNetworkFactory,
								   final CyNetworkManager cyNetworkManager,
								   final CyRootNetworkManager cyRootNetworkManager) {
		if (inputStream == null)
			throw new NullPointerException("Input stream is null");
		if (cyApplicationManager == null)
			throw new NullPointerException("CyApplicationManager is null");
		if (cyNetworkFactory == null)
			throw new NullPointerException("CyNetworkFactory is null");
		if (cyRootNetworkManager == null)
			throw new NullPointerException("CyRootNetworkManager is null");
		
		this.inputStream = inputStream;
		this.cyApplicationManager = cyApplicationManager;
		this.cyNetworkViewFactory = cyApplicationManager.getDefaultNetworkViewRenderer().getNetworkViewFactory();
		this.cyNetworkFactory = cyNetworkFactory;
		this.cyNetworkManager = cyNetworkManager;
		this.cyRootNetworkManager = cyRootNetworkManager;
		this.cyApplicationManager = cyApplicationManager;
		
		init();
	}
	
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
		if (cyRootNetworkManager == null)
			throw new NullPointerException("CyRootNetworkManager is null");

		this.inputStream = inputStream;
		this.cyNetworkViewFactory = cyNetworkViewFactory;
		this.cyNetworkFactory = cyNetworkFactory;
		this.cyNetworkManager = cyNetworkManager;
		this.cyRootNetworkManager = cyRootNetworkManager;
		
		init();
	}
	
	private void init() {
		name2RootMap = getRootNetworkMap();
		nodeMap = new HashMap<>(10000);
		
		// initialize the network Collection
		final List<String> rootNames = new ArrayList<>();
		rootNames.addAll(name2RootMap.keySet());
		
		if (!rootNames.isEmpty()) {
			sort(rootNames);
			rootNames.add(0, CREATE_NEW_COLLECTION_STRING);
		}
		
		final ListSingleSelection<String> rootNetList = new ListSingleSelection<>(rootNames);
		
		final CyNetwork net = cyApplicationManager != null ? cyApplicationManager.getCurrentNetwork() : null;
		final CyRootNetwork rootNet = net != null ? cyRootNetworkManager.getRootNetwork(net) : null;
		final String name = rootNet != null ?
				rootNet.getRow(rootNet).get(CyRootNetwork.NAME, String.class) : CREATE_NEW_COLLECTION_STRING;
		
		if (rootNames.contains(name))
			rootNetList.setSelectedValue(name);
		else if (rootNames.contains(CREATE_NEW_COLLECTION_STRING))
			rootNetList.setSelectedValue(CREATE_NEW_COLLECTION_STRING);

		setRootNetworkList(rootNetList);
		
		// initialize renderer list
		final List<NetworkViewRenderer> renderers = new ArrayList<>();
		NetworkViewRenderer defViewRenderer = null;
		
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
			defViewRenderer = cyApplicationManager.getDefaultNetworkViewRenderer();
		}
		
		rendererList = new ListSingleSelection<>(renderers);
		
		if (defViewRenderer != null && renderers.contains(defViewRenderer))
			rendererList.setSelectedValue(defViewRenderer);
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
		CyRootNetwork rootNetwork = null;
		final String rootNetName = rootNetworkList.getSelectedValue();
		
		if (rootNetName != null)
			rootNetwork = name2RootMap.get(rootNetName);
		
		// Initialize the map of nodes only when we add network to existing collection.
		if (rootNetwork != null)
			initNodeMap(rootNetwork);
		
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
		final String rootNetName = rootNetworkList.getSelectedValue();
		
		if (rootNetName == null || rootNetName.equalsIgnoreCase(CREATE_NEW_COLLECTION_STRING))
			return;

		String targetKeyColName = targetColumnList.getSelectedValue();
		
		if (targetKeyColName == null)
			targetKeyColName = CyRootNetwork.SHARED_NAME;
		
		final Iterator<CyNode> it = rootNetwork.getNodeList().iterator();
		
		while (it.hasNext()) {
			CyNode node = it.next();
			Object keyValue = rootNetwork.getRow(node).getRaw(targetKeyColName);
			nodeMap.put(keyValue, node);
		}
	}

	private final ListSingleSelection<String> getTargetColumns(final CyNetwork network) {
		final CyTable selectedTable = network.getTable(CyNode.class, CyRootNetwork.SHARED_ATTRS);
		final List<String> colNames = new ArrayList<>();
		
		for (CyColumn col : selectedTable.getColumns()) {
			// Exclude SUID from the mapping key list
			if (!col.getName().equalsIgnoreCase(CyIdentifiable.SUID) && !col.getName().endsWith(".SUID") &&
					(col.getType() == String.class || col.getType() == Integer.class || col.getType() == Long.class))
				colNames.add(col.getName());
		}
		
		if (colNames.isEmpty() || (colNames.size() == 1 && colNames.contains(CyRootNetwork.SHARED_NAME)))
			return new ListSingleSelection<>();
		
		sort(colNames);
		
		return new ListSingleSelection<>(colNames);
	}
	
	private final Map<String, CyRootNetwork> getRootNetworkMap() {
		final Map<String, CyRootNetwork> name2RootMap = new HashMap<>();

		if (cyNetworkManager != null) { // We don't want to get the existing (old) networks when loading a new session
			for (CyNetwork net : cyNetworkManager.getNetworkSet()) {
				final CyRootNetwork rootNet = cyRootNetworkManager.getRootNetwork(net);
				
				if (!name2RootMap.containsValue(rootNet))
					name2RootMap.put(rootNet.getRow(rootNet).get(CyRootNetwork.NAME, String.class), rootNet);
			}
		}

		return name2RootMap;
	}

	private void sort(final List<String> names) {
		if (!names.isEmpty()) {
			final Collator collator = Collator.getInstance(Locale.getDefault());
			
			Collections.sort(names, new Comparator<String>() {
				@Override
				public int compare(String s1, String s2) {
					if (s1 == null && s2 == null) return 0;
					if (s1 == null) return -1;
					if (s2 == null) return 1;
					return collator.compare(s1, s2);
				}
			});
		}
	}
}

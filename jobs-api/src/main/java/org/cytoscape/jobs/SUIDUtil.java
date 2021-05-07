package org.cytoscape.jobs;

/*
 * #%L
 * Cytoscape Jobs API (jobs-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2021 The Cytoscape Consortium
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

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.cytoscape.jobs.CyJob;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyRow;
import org.cytoscape.model.CyTable;

/**
 * This utility class provides methods to save and restore SUIDs that
 * are sent to remote execution environments across sessions.
 *
 * @CyAPI.Class
 * @CyAPI.InModule jobs-api
 */
public class SUIDUtil {
	enum Identifiable { NETWORK, NODE, EDGE, UNKNOWN };

	/**
	 * This method may be used to save a list of SUIDs that have been sent to a remote
	 * service so that they may be restored later
	 *
	 * @param job the job that we're saving the SUIDs for
	 * @param network the network that the SUIDs are part of
	 * @param objs the Cytoscape objects whose SUIDs we want to save
	 */
	static public void saveSUIDs(CyJob job, CyNetwork network, List<? extends CyIdentifiable> objs) {
		Map<Identifiable, CyTable> suidMap = new HashMap<>();
		// Add the network table to the map, we depend on having the network
		// available to us for the restore
		String columnName = getColumnName(job);

		// First, make sure we've saved the network SUID
		CyTable hiddenTable = createColumn(network, Identifiable.NETWORK, columnName);
		CyRow netRow = hiddenTable.getRow(network.getSUID());
		if (netRow != null)
			netRow.set(columnName, network.getSUID());

		for (CyIdentifiable cyId: objs) {
			Identifiable type = getType(cyId);
			if (!suidMap.containsKey(type)) {
				hiddenTable = createColumn(network, type, columnName);
				if (hiddenTable != null)
					suidMap.put(type, hiddenTable);
			}
			CyRow row = suidMap.get(type).getRow(cyId.getSUID());
			if (row != null)
				row.set(columnName, cyId.getSUID());
		}

	}

	/**
	 * This method is used to restore a saved network based on the network SUID.  This takes
	 * some special handling, since our access to the network tables are through the network
	 * itself, we need to use the network manager to get the list of networks in order to
	 * find the right network.
	 *
	 * @param job the {@link CyJob} we're restoring
	 * @param netManager the {@link CyNetworkManager} to get the list of networks
	 * @param networkSUID the SUIDof the network we're restoring
	 * @param clear if true, remove the column for this job
	 * @return the restored network or null if we can't find it
	 */
	static public CyNetwork restoreNetwork(CyJob job, CyNetworkManager netManager, Long networkSUID, boolean clear) {
		String columnName = getColumnName(job);
		for (CyNetwork network: netManager.getNetworkSet()) {
			CyTable table = network.getTable(CyNetwork.class, CyNetwork.HIDDEN_ATTRS);
			if (table.getColumn(columnName) == null)
				continue;
			if (table.getRow(network.getSUID()).get(columnName, Long.class).equals(networkSUID)) {
				if (clear)
					table.deleteColumn(columnName);
				return network;
			}
		}
		return null;
	}

	/**
	 * Method to restore Cytoscape objects based on a list of SUIDs that were sent to the remote
	 * service as saved in the hidden table. 
	 *
	 * @param job the {@link CyJob} we're restoring
	 * @param network the network the SUIDs are part of.  See {@link SaveSUID.restoreNetwork()} for
	 * a method to get the network
	 * @param oldIds the list of old SUIDs that were sent to the remote server and saved
	 * in the session
	 * @param clear if true, remove the columns for this job
	 * @return a map that relates the original id to the Cytoscape object
	 */
	static public Map<Long,CyIdentifiable> restoreSUIDs(CyJob job, CyNetwork network, List<Long> oldIds, boolean clear) {
		Map<Long,CyIdentifiable> objMap = new HashMap<>();
		Set<Long> suidSet = new HashSet<Long>(oldIds);

		String columnName = getColumnName(job);

		// First, determine which types have our column
		CyTable networkTable = network.getTable(CyNetwork.class, CyNetwork.HIDDEN_ATTRS);
		if (networkTable.getColumn(columnName) != null) {
			Long id = network.getRow(network).get(columnName, Long.class);
			if (suidSet.contains(id))
				objMap.put(id,network);
			if (clear)
				networkTable.deleteColumn(columnName);
		}

		CyTable nodeTable = network.getTable(CyNode.class, CyNetwork.HIDDEN_ATTRS);
		if (nodeTable.getColumn(columnName) != null) {
			for (CyNode node: network.getNodeList()) {
				Long id = nodeTable.getRow(node.getSUID()).get(columnName, Long.class);
				if (id == null) continue;
				if (suidSet.contains(id)) {
					objMap.put(id,node);
				}
			}
			if (clear)
				nodeTable.deleteColumn(columnName);
		}

		CyTable edgeTable = network.getTable(CyEdge.class, CyNetwork.HIDDEN_ATTRS);
		if (edgeTable.getColumn(columnName) != null) {
			for (CyEdge edge: network.getEdgeList()) {
				Long id = edgeTable.getRow(edge.getSUID()).get(columnName, Long.class);
				if (id == null) continue;
				if (suidSet.contains(id))
					objMap.put(id,edge);
			}
			if (clear)
				edgeTable.deleteColumn(columnName);
		}
		return objMap;
	}

	static private Identifiable getType(CyIdentifiable cyId) {
		if (CyNetwork.class.isAssignableFrom(cyId.getClass())) {
			return Identifiable.NETWORK;
		} else if (CyNode.class.isAssignableFrom(cyId.getClass())) {
			return Identifiable.NODE;
		} else if (CyEdge.class.isAssignableFrom(cyId.getClass())) {
			return Identifiable.EDGE;
		}
		return Identifiable.UNKNOWN;
	}

	static private String getColumnName(CyJob job) {
		String columnName = job.getJobName()+"("+job.getJobId()+")_SUID";
		return columnName;
	}

	static private CyTable createColumn(CyNetwork network, Identifiable type,
										                  String columnName) {
		Class<? extends CyIdentifiable> clazz = null;
		switch (type) {
			case NETWORK:
				clazz = CyNetwork.class;
				break;
			case NODE:
				clazz = CyNode.class;
				break;
			case EDGE:
				clazz = CyEdge.class;
				break;
			case UNKNOWN:
				return null;
		}
		CyTable table = network.getTable(clazz, CyNetwork.HIDDEN_ATTRS);
		if (table.getColumn(columnName) == null)
			table.createColumn(columnName, Long.class, false);
		return table;
	}
}

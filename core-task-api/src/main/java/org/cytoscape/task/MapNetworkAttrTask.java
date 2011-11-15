package org.cytoscape.task;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.model.CyTable;
import org.cytoscape.model.CyTableEntry;
import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.TaskMonitor;
import org.cytoscape.work.Tunable;

/** 
 * This is a simple {@link org.cytoscape.work.Task} that will take a loaded table and ask whether
 * the columns in the new table should become virtual columns in the node or
 * edge table of the current network, all networks, or no networks.
 * @CyAPI.Final.Class
 */
public final class MapNetworkAttrTask extends AbstractTask {
	/**
	 * If true map to current network only.
	 */
	@Tunable(description="Map to current network only")
	public boolean currentNetworkOnly = true;

	private final Class<? extends CyTableEntry> type; // Must be node or edge!
	private final CyTable newGlobalTable;
	private final CyNetworkManager networkManager;
	private final CyApplicationManager applicationManager;
	private String mappingKey; 

	/**
	 * Constructor.
	 * @param type The type of table to map to, either CyNode.class or CyEdge.class.
	 * @param newGlobalTable The table to be mapped. 
	 * @param mappingKey The column name in the existing table used to join with the primary key in the new table.
	 * @param networkManager The network manager used to access the list of all networks. 
	 * @param applicationManager The application manager used to access the current network. 
	 */
	public MapNetworkAttrTask(final Class<? extends CyTableEntry> type, 
	                          final CyTable newGlobalTable,
	                          String mappingKey,
	                          final CyNetworkManager networkManager,
	                          final CyApplicationManager applicationManager)
	{
		this.type               = type;
		this.newGlobalTable     = newGlobalTable;
		this.mappingKey         = mappingKey;		
		this.networkManager     = networkManager;
		this.applicationManager = applicationManager;

		if (type != CyNode.class && type != CyEdge.class)
			throw new IllegalArgumentException("\"type\" must be CyNode.class or CyEdge.class!");
	}

	
	/**
	 * Constructor. Will attempt to map existing tables based on the {@link CyTableEntry#NAME}
	 * column.
	 * @param type The type of table to map to, either CyNode.class or CyEdge.class.
	 * @param newGlobalTable The table to be mapped. 
	 * @param networkManager The network manager used to access the list of all networks. 
	 * @param applicationManager The application manager used to access the current network. 
	 */
	public MapNetworkAttrTask(final Class<? extends CyTableEntry> type, 
	                          final CyTable newGlobalTable,
	                          final CyNetworkManager networkManager,
	                          final CyApplicationManager applicationManager)
	{
		this(type,newGlobalTable,CyTableEntry.NAME,networkManager, applicationManager);
	}


	/**
	 * Executes the task.
	 * @param taskMonitor The TaskMonitor used to track the state of the task execution.
	 * @throws Exception All Exceptions throw will be caught and handled by the 
	 * {@link org.cytoscape.work.TaskManager} executing the task.
	 */
	public void run(final TaskMonitor taskMonitor) throws Exception {
		taskMonitor.setTitle("Mapping virtual columns");

		final List<CyTable> targetTables = new ArrayList<CyTable>();

		if (currentNetworkOnly) {
			final CyNetwork currentNetwork = applicationManager.getCurrentNetwork();
			targetTables.add(type == CyNode.class ? currentNetwork.getDefaultNodeTable()
					                      : currentNetwork.getDefaultEdgeTable());
		} else {
			final Set<CyNetwork> networks = networkManager.getNetworkSet();
			for (final CyNetwork network : networks)
				targetTables.add(type == CyNode.class ? network.getDefaultNodeTable()
						                      : network.getDefaultEdgeTable());
		}
		
		mapAll(targetTables);
	}

	
	private void mapAll(final List<CyTable> targetTables) {
		if (targetTables.isEmpty())
			return;

		if (newGlobalTable.getPrimaryKey().getType() != String.class)
			throw new IllegalStateException("The new table's primary key is not of type String!");
		final String sourceTableJoinColumn = newGlobalTable.getPrimaryKey().getName();

		for (final CyTable targetTable : targetTables) {
			if (cancelled)
				return;
			targetTable.addVirtualColumns(newGlobalTable, mappingKey, false);			
		}
	}
}

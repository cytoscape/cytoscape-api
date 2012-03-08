	package org.cytoscape.task;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.model.CyTable;
import org.cytoscape.model.CyColumn;
import org.cytoscape.model.CyTableEntry;
import org.cytoscape.model.subnetwork.CyRootNetworkManager;
import org.cytoscape.model.subnetwork.CyRootNetwork;
import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.ProvidesTitle;
import org.cytoscape.work.TaskMonitor;
import org.cytoscape.work.Tunable;
import org.cytoscape.work.util.ListSingleSelection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * This is a simple {@link org.cytoscape.work.Task} that will take a loaded table and ask whether
 * the columns in the new table should become virtual columns in the node or
 * edge table of the current network, all networks, or no networks.
 * @CyAPI.Final.Class
 */
public final class MapNetworkAttrTask extends AbstractTask {

	private static final Logger logger = LoggerFactory.getLogger(MapNetworkAttrTask.class);

	private static final String CURRENT_LOCAL = "Only to the current network";
	private static final String CURRENT_SHARED = "All networks related to current network";
	private static final String ALL_SHARED = "All networks";
	private static final String INDEPENDENT = "None (create Global Table w/o mapping)";

	@Tunable(description = "Would you like to map this table to:")
	public static final ListSingleSelection<String> whichTable = new ListSingleSelection<String>(CURRENT_SHARED,
			CURRENT_LOCAL, ALL_SHARED, INDEPENDENT);
	static {
		// Default selected item is map to local.
		whichTable.setSelectedValue(CURRENT_LOCAL);
	}
	
	@ProvidesTitle
	public String getTitle() {
		return "Map Table";
	}
	
	private final Class<? extends CyTableEntry> type; // Must be node or edge!
	private final CyTable newGlobalTable;
	private final CyNetworkManager networkManager;
	private final CyApplicationManager applicationManager;
	private final CyRootNetworkManager rootNetworkManager;
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
	                          final CyApplicationManager applicationManager,
							  final CyRootNetworkManager rootNetworkManager)
	{
		this.type               = type;
		this.newGlobalTable     = newGlobalTable;
		this.mappingKey         = mappingKey;		
		this.networkManager     = networkManager;
		this.applicationManager = applicationManager;
		this.rootNetworkManager = rootNetworkManager;

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
	                          final CyApplicationManager applicationManager,
							  final CyRootNetworkManager rootNetworkManager)
	{
		this(type,newGlobalTable,CyTableEntry.NAME,networkManager,applicationManager,rootNetworkManager);
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
		final String selection = whichTable.getSelectedValue();
		
		if (selection.equals(CURRENT_LOCAL)) {
			final CyNetwork currentNetwork = applicationManager.getCurrentNetwork();
			targetTables.add(type == CyNode.class ? currentNetwork.getDefaultNodeTable()
					                      : currentNetwork.getDefaultEdgeTable());
		} else if (selection.equals(CURRENT_SHARED)) {
			final CyNetwork currentNetwork = applicationManager.getCurrentNetwork();
			final CyRootNetwork rootNetwork = rootNetworkManager.getRootNetwork(currentNetwork);
			targetTables.add(type == CyNode.class ? rootNetwork.getSharedNodeTable()
					                      : rootNetwork.getSharedEdgeTable());
			if ( mappingKey.equals(CyTableEntry.NAME) )
				mappingKey = CyRootNetwork.SHARED_NAME;
		} else if (selection.equals(ALL_SHARED)) {
			final Set<CyNetwork> networks = networkManager.getNetworkSet();
			final Set<CyRootNetwork> rootNetworks = new HashSet<CyRootNetwork>(); 
			for (final CyNetwork network : networks) 
				rootNetworks.add( rootNetworkManager.getRootNetwork(network) );
			for (final CyRootNetwork rootNetwork : rootNetworks) 
				targetTables.add(type == CyNode.class ? rootNetwork.getSharedNodeTable()
						                      : rootNetwork.getSharedEdgeTable());
			if ( mappingKey.equals(CyTableEntry.NAME) )
				mappingKey = CyRootNetwork.SHARED_NAME;
		} else {
			// don't map it to anything!
			return;
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
			CyColumn trgCol = targetTable.getColumn(mappingKey);
			if ( trgCol != null )
				targetTable.addVirtualColumns(newGlobalTable, mappingKey, false);			
			else
				logger.warn("Table: '" + targetTable.getTitle() + "' does not contain a column named: '" + 
				            mappingKey + "' so no mapping is possible!");
		}
	}
}

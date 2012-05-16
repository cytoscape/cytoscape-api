package org.cytoscape.task.edit;

import java.util.List;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyTable;
import org.cytoscape.task.TableTaskFactory;
import org.cytoscape.work.TaskIterator;

public interface MapTableToNetworkTablesTaskFactory extends TableTaskFactory{
	
	TaskIterator createTaskIterator(final CyTable globalTable, List<CyNetwork> networkList, String tableType);
	
}
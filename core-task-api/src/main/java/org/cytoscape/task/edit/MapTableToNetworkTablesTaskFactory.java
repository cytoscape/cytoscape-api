package org.cytoscape.task.edit;

import org.cytoscape.model.CyTable;
import org.cytoscape.task.TableTaskFactory;
import org.cytoscape.work.TaskIterator;

public interface MapTableToNetworkTablesTaskFactory extends TableTaskFactory{
	
	TaskIterator createTaskIterator(final CyTable globalTable, String tableType);
	
}
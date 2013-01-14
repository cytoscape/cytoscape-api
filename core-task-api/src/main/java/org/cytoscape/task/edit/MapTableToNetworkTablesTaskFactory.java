package org.cytoscape.task.edit;

import java.util.List;

import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyTable;
import org.cytoscape.task.TableTaskFactory;
import org.cytoscape.work.TaskIterator;

/**
 * TODO: Missing documentation
 * @CyAPI.Api.Interface
 * @CyAPI.InModule core-task-api
 */
public interface MapTableToNetworkTablesTaskFactory extends TableTaskFactory{
	
	TaskIterator createTaskIterator(final CyTable globalTable, boolean selectedNetworksOnly,  List<CyNetwork> networkList,  Class<? extends CyIdentifiable> tableType);
	
}
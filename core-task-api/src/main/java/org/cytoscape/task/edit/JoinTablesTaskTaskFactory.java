package org.cytoscape.task.edit;

import java.util.List;

import org.cytoscape.model.CyColumn;
import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyTable;
import org.cytoscape.model.subnetwork.CyRootNetwork;
import org.cytoscape.task.TableTaskFactory;
import org.cytoscape.work.TaskIterator;

public interface JoinTablesTaskTaskFactory extends TableTaskFactory{

	TaskIterator createTaskIterator(final CyTable globalTable, boolean selectedNetworksOnly,  List<CyNetwork> networkList, CyRootNetwork rootNetwork, CyColumn targetJoinColumn, Class<? extends CyIdentifiable> tableType);
	
}

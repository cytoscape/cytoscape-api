package org.cytoscape.task.export.table;

import java.io.File;

import org.cytoscape.task.NetworkViewTaskFactory;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.work.TaskIterator;

public interface EdgeTableExporter extends NetworkViewTaskFactory{

	TaskIterator createTaskIterator(CyNetworkView view, final File outputFile);

}

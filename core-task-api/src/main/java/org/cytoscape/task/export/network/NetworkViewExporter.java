package org.cytoscape.task.export.network;

import java.io.File;

import org.cytoscape.task.NetworkViewTaskFactory;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.work.TaskIterator;

public interface NetworkViewExporter extends NetworkViewTaskFactory{
	TaskIterator createTaskIterator(CyNetworkView view, File file);

}

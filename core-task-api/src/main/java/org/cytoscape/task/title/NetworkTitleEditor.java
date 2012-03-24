package org.cytoscape.task.title;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.task.NetworkTaskFactory;
import org.cytoscape.work.TaskIterator;

public interface NetworkTitleEditor extends NetworkTaskFactory{
	
	TaskIterator createTaskIterator (CyNetwork network, String title);
}

package org.cytoscape.task.select;

import java.io.File;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.task.NetworkTaskFactory;
import org.cytoscape.work.TaskIterator;

public interface FromFileListSelecter extends NetworkTaskFactory {
	TaskIterator createTaskIterator(CyNetwork network, final File file);


}

package org.cytoscape.task.write;

import java.io.File;

import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskIterator;

public interface ExportSelectedNetworkTaskFactory extends TaskFactory {
	/**
	 * Creates the task iterator for exporting the selected network. Notice that this task will 
	 * be synced with the current thread and it will not create a new thread or a new task monitor.
	 * @param file The file to store the exported network.
	 * @return Task iterator for running the task.
	 */
	TaskIterator createTaskIterator(File file);
}

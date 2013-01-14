package org.cytoscape.task.write;

import java.io.File;

import org.cytoscape.task.NetworkViewTaskFactory;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.work.TaskIterator;

/**
 * This interface provides a task iterator for exporting network views.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule core-task-api
 */
public interface ExportNetworkViewTaskFactory extends NetworkViewTaskFactory{
	
	/**
	 * Creates the task iterator for exporting network views. Notice that this task will be synced with 
	 * the current thread and it will not create a new thread or a new task monitor.
	 * @param view Selected view for export.
	 * @param file The file to store the exported view.
	 * @return Task iterator for running the task.
	 */
	TaskIterator createTaskIterator(CyNetworkView view, File file);

}

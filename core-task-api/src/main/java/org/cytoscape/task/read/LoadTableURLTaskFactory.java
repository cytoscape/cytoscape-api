package org.cytoscape.task.read;

import java.net.URL;

import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskIterator;


/**
 * This interface provides a task iterator for loading an attribute URL to a table.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule core-task-api
 */
public interface LoadTableURLTaskFactory extends TaskFactory {
	
	/**
	 * Creates a task iterator for loading an attribute URL to the global table. The created task
	 * runs synchronously in the current thread and does not create a task monitor.
	 * @param url The attribute URL to load into the global table.
	 * @return a task iterator of type {@link TaskIterator}.
	 */
	TaskIterator createTaskIterator(final URL url);


}

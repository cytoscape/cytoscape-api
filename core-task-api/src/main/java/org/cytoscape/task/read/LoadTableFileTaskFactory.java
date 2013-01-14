package org.cytoscape.task.read;

import java.io.File;

import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskIterator;


/**
 * This interface provides a task iterator for loading the attribute file to a table.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule core-task-api
 */
public interface LoadTableFileTaskFactory extends TaskFactory {
	
	/**
	 * Creates the task iterator for loading an attribute file to a table.
	 * The created task runs synchronously in the current thread and does not create
	 * a task monitor.
	 * @param file The attribute file to be loaded into a table.
	 * @return a task iterator of type {@link TaskIterator}.
	 */
	TaskIterator createTaskIterator(final File file);

}

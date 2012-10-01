package org.cytoscape.task.read;

import java.io.File;

import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskIterator;


/**
 * This interface provides a task iterator for loading networks from files.
 * @CyAPI.Api.Interface
 */
public interface LoadNetworkFileTaskFactory extends TaskFactory{
	
	/**
	 * Create a task iterator for loading a network from a file.
	 * The created task runs synchronously in the current thread and does not
	 * create a task monitor.
	 * @param file The file for loading into a network
	 * @return a task iterator of type {@link TaskIterator}
	 */
	TaskIterator creatTaskIterator(final File file);

}

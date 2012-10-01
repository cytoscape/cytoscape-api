package org.cytoscape.task.write;

import java.io.File;

import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskIterator;

/**
 * This interface provides a task iterator for saving a session in a
 * specified file.
 * @CyAPI.Api.Interface
 */
public interface SaveSessionAsTaskFactory extends TaskFactory{
	
	/**
	 * Creates a task iterator for saving the session in a specified file.
	 * @param file The file that the session is written on.
	 * @return a task iterator of type {@link TaskIterator}.
	 */
	TaskIterator createTaskIterator(final File file);
}

package org.cytoscape.task.read;

import java.io.File;

import org.cytoscape.session.CySession;
import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskIterator;

/**
 * This interface provides a task iterator for loading a session.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule core-task-api
 */
public interface OpenSessionTaskFactory extends TaskFactory {
	
	/**
	 * Creates a task iterator for loading a session from a file.
	 * @param file The input file for loading the session from.
	 * @return a task iterator of type {@link TaskIterator}. 
	 */
	TaskIterator createTaskIterator(final File file);

}

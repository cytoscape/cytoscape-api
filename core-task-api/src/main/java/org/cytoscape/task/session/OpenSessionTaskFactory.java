package org.cytoscape.task.session;

import java.io.File;

import org.cytoscape.session.CySession;
import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskIterator;

/**
 * This interface provides a task iterator for loading a session.
 * @CyAPI.Api.Interface
 */
public interface OpenSessionTaskFactory extends TaskFactory {
	
	/**
	 * Creates a task iterator for loading a session from a file.
	 * @param file The input file for loading the session from.
	 * @return a task iterator of type {@link TaskIterator}. 
	 */
	TaskIterator createTaskIterator(final File file);

}

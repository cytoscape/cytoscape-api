package org.cytoscape.task.session;

import java.io.File;

import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskIterator;

/**
 * This interface provides a task iterator for saving a session in a
 * specified file.
 * @author rozagh
 *
 */
public interface SaveSessionAsTaskFactory extends TaskFactory{
	
	/**
	 * Creates a task iterator for saving the session in a specified file.The 
	 * created task will run synchronously in the current thread and will not
	 * create a task monitor. 
	 * @param file The file that the session is written on.
	 * @return a task iterator of type {@link TaskIterator}.
	 */
	TaskIterator createTaskIterator(final File file);
}

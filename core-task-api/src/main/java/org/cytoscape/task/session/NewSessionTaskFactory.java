package org.cytoscape.task.session;


import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskIterator;

/**
 * This interface provides a task iterator for creating a new session.
 * @author rozagh
 *
 */
public interface NewSessionTaskFactory extends TaskFactory {
	

	/**
	 * Creates a task iterator for creating anew session and
	 *  destroying the current session based on the input.The created
	 *  task runs synchronously in the current thread and it will not 
	 *  create a task monitor. 
	 * @param destroyCurrentSession
	 * @return
	 */
	TaskIterator createTaskIterator(final boolean destroyCurrentSession);

}

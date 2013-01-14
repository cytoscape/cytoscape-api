package org.cytoscape.task.create;


import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskIterator;

/**
 * This interface provides a task iterator for creating a new session.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule core-task-api
 */
public interface NewSessionTaskFactory extends TaskFactory {
	
	/**
	 * Creates a task iterator for creating a new session and
	 * destroying the current session based on the input.
	 * @param destroyCurrentSession Whether or not to destroy the current session.
	 * @return A TaskIterator object containing one or more {@link org.cytoscape.work.Task} objects to execute. 
	 */
	TaskIterator createTaskIterator(boolean destroyCurrentSession);
}

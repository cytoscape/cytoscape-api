package org.cytoscape.work;


/**
 * Executes the Tasks found in the TaskIterator provided by a TaskFactory. 
 * @CyAPI.Api.Interface
 */
public interface TaskManager<T,C> {

	/**
	 * Returns a configuration object generated from the Tunables
	 * read from the TaskFactory.
	 * @param factory The TaskFactory that will be scanned for Tunables.
	 * @return a configuration object generated from the Tunables
	 * read from the TaskFactory.
	 */
	 T getConfiguration(TaskFactory factory, Object tunableContext);

	/**
	 * Allows a user of a TaskManager to set the execution context for
	 * the task, for example the parent Window of a dialog or the top-level
	 * menu for menu generation.
	 * @param context The object to serve as the execution context for the TaskManager.
	 */
	void setExecutionContext(C context);

	/**
	 * This method is called to execute the Tasks in a TaskIterator provided
	 * by a <code>TaskFactory</code>.  
	 * This method returns once the <code>Task</code>s derived from the <code>TaskIterator</code>
	 * returned by the <code>TaskFactory</code>'s <code>createTaskIterator()</code> method have
	 * started (but not necessarily completed) execution. 
	 * It <i>does not wait</i> for the <code>Task</code>s to finish. 
	 * @param factory The <code>TaskFactory</code> whose tasks returned by its createTaskIterator()
	 * method's iterator will be executed
	 */
	void execute(TaskIterator iterator);
}

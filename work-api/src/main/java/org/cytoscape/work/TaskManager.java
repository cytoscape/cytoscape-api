package org.cytoscape.work;


/**
 * Executes the {@link Task}s found in the {@link TaskIterator} provided by a {@link TaskFactory}. 
 * @CyAPI.Api.Interface
 * @CyAPI.InModule work-api
 */
public interface TaskManager<T,C> {

	/**
	 * Returns a configuration object generated from the {@link Tunable}s
	 * read from the {@link TaskFactory}.
	 * @param factory The {@link TaskFactory} that will be scanned for {@link Tunable}s.
	 * @param tunableContext An object providing context for the {@link Tunable}s. 
	 * @return a configuration object generated from the {@link Tunable}s
	 * read from the {@link TaskFactory}.
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
	 * This method is called to execute the {@link Task}s in a {@link TaskIterator} provided
	 * by a {@link TaskFactory}.  
	 * This method returns once the {@link Task}s derived from the {@link TaskIterator}
	 * returned by the {@link TaskFactory}'s <code>createTaskIterator()</code> method have
	 * started (but not necessarily completed) execution. 
	 * It <i>does not wait</i> for the {@link Task}s to finish. 
	 * @param iterator The {@link TaskIterator} whose tasks will be executed.
	 */
	void execute(TaskIterator iterator);
}

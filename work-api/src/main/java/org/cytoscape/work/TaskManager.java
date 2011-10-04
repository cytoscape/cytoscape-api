package org.cytoscape.work;


/**
 * Executes the Tasks found in the TaskIterator provided by a TaskFactory. 
 */
public interface TaskManager {

	/** 
	 * Tests an object for having tunable annotations.
	 * @param o object to test for having tunable annotations.
	 * @return true if "o" has tunable annotations and else false.
	 */
	boolean hasTunables(final Object o);

	/**
	 * This method is called to execute the Tasks in a TaskIterator provided
	 * by a <code>TaskFactory</code>.  
	 * This method returns once the <code>Task</code>s derived from the <code>TaskIterator</code>
	 * returned by the <code>TaskFactory</code>'s <code>getTaskIterator()</code> method have
	 * started (but not necessarily completed) execution. 
	 * It <i>does not wait</i> for the <code>Task</code>s to finish. 
	 * @param factory The <code>TaskFactory</code> whose tasks returned by its getTaskIterator()
	 * method's iterator will be executed
	 */
	void execute(TaskFactory factory);
}

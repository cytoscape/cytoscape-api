package org.cytoscape.work;

import java.util.Arrays;
import java.util.List;

/**
 * A <code>Task</code> that notifies its observers when it is finished
 * executing.
 * 
 * @param <R> The type of the value this <code>Task</code> produces.
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule work-api
 */
public interface ObservableTask extends Task {
	/**
 	 * Return the results from this task (if any)
 	 *
 	 * @param type the class type of the returned results.  This
 	 * is primarily used to request that the Task format the return
 	 * as a String, but other types are possible also.  All ObservableTasks
 	 * should be able to handle String requests.
 	 * @return the Task results, or null if there are no results
 	 */
	public <R> R getResults(Class <? extends R> type);
	
	default List<Class<?>> getResultClasses() {return Arrays.asList(String.class); };
}

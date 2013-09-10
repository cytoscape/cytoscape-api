package org.cytoscape.work;

/**
 * An observer that gets notified when an <code>ObservableTask</code> finishes
 * executing.  If the task finishes, <code>taskFinished()</code> is
 * called with whatever result the task produces, if any. When a task iterator finishes,
 * the task manager will invoke {@code allFinished} with the appropriate {@code FinishStatus}
 * object depending on the scenario in which the task iterator finished.
 *
 * @param <R> The type of the result this observer is expected to receive.
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule work-api
 */
public interface TaskObserver {
	/**
	 * Called by an <code>ObservableTask</code> when it is finished executing.
	 *
	 * @param task The task being observed
	 */
	public void taskFinished(ObservableTask task);

	/**
 	 * Called by a <code>TaskManager</code> to tell us that the task iterator has completed.
     * @param finishStatus Indicates how the task iterator completed.
 	 */
	public void allFinished(FinishStatus finishStatus);
}

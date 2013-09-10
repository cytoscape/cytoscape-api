package org.cytoscape.work;

/**
 * An observer that gets notified when an <code>ObservableTask</code> finishes
 * executing.  If the task finishes, <code>taskFinished()</code> is
 * called with whatever result the task produces, if any. When a task iterator finishes,
 * the task manager will invoke {@code allFinished}, {@code cancelled}, or {@code failed}, depending
 * on the situation.
 *
 * <p>
 * When a task iterator invoked with a {@code TaskObserver} finishes,
 * {@code TaskManager}s are required to call one and only one of these methods:
 * {@code allFinished}, {@code cancelled}, or {@code failed}.
 * </p>
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
 	 */
	public void allFinished(FinishStatus finishStatus);
}

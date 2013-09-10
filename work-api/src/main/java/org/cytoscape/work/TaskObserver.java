package org.cytoscape.work;

/**
 * An observer that gets notified when an <code>ObservableTask</code> finishes
 * executing.  If the task finishes, <code>taskFinished()</code> is
 * called with whatever result the task produces, if any. When a task iterator finishes,
 * the task manager will invoke {@code allFinished}, {@code cancelled}, or {@code failed}, depending
 * on the situation.
 *
 * <p>
 * <em>Threading.</em> {@code TaskManager}s are
 * required to call the {@code TaskObserver}'s methods on the same thread
 * as the task iterator's.
 * </p>
 *
 * <p>
 * <em>The task manager's requirement.</em>
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
 	 * Called by a <code>TaskManager</code> to tell us that all of the tasks in an iterator had completed successfully.
 	 */
	public void allFinished();

	/**
 	 * Called by a <code>TaskManager</code> to tell us that the given task was cancelled by the user.
 	 */
    public void cancelled(Task cancelledTask);

	/**
 	 * Called by a <code>TaskManager</code> to tell us that the given task failed to complete because it threw an exception.
 	 */
    public void failed(Task failedTask, Exception e);
}

package org.cytoscape.work;

/**
 * An observer that gets notified when an <code>ObservableTask</code> finishes
 * executing.  If the task does not start or finish processing, this observer
 * is not notified.  If the task does finish, <code>taskFinished()</code> is
 * called with whatever result the task produces, if any. 
 *
 * @param <R> The type of the result this observer is expected to receive.
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule work-api
 */
public interface TaskObserver<R> {
	/**
	 * Called by an <code>ObservableTask</code> when it is finished executing.
	 *
	 * @param result The result, if any, produced by the
	 *               <code>ObservableTask</code> being observed.
	 */
	void taskFinished(R result);
}

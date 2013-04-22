package org.cytoscape.work;

/**
 * A <code>Task</code> that notifies its observers when it is finished
 * executing.
 * 
 * @param <R> The type of the value this <code>Task</code> produces.
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule work-api
 */
public interface ObservableTask<R> extends Task {
	/**
	 * Adds an observer to this <code>Task</code>.  The observer's
	 * <code>taskFinished()</code> methods is only called if and when
	 * the <code>Task</code> finishes executing.
	 * 
	 * @param observer The observer that should be added.
	 */
	void addObserver(TaskObserver<R> observer);
	
	/**
	 * Removes an observer from this <code>Task</code>.  The observer will
	 * no longer be notified when the <code>Task</code> finishes executing.
	 * 
	 * @param observer The observer that should be removed.
	 */
	void removeObserver(TaskObserver<R> observer);
}

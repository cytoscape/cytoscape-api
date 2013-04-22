package org.cytoscape.work;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * An implementation of <code>ObservableTask</code> where observers are
 * notified in the order they were added.  A particular observer can only be
 * added at most once to this <code>Task</code>.
 * 
 * @param <R> The type of the value this <code>Task</code> produces.
 * 
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule work-api
 */
public abstract class AbstractObservableTask<R> extends AbstractTask implements ObservableTask<R> {
	private Set<TaskObserver<R>> observers;

	/**
	 * Creates a new <code>AbstractObservableTask></code> with no observers.
	 */
	protected AbstractObservableTask() {
		observers = new LinkedHashSet<TaskObserver<R>>();
	}
	
	@Override
	public void addObserver(TaskObserver<R> observer) {
		observers.add(observer);
	}
	
	@Override
	public void removeObserver(TaskObserver<R> observer) {
		observers.remove(observer);
	}
	
	/**
	 * Notifies all the observers that this <code>Task</code> has finished
	 * executing.  This should be called by subclasses of
	 * <code>AbstractObservableTask</code>.
	 * 
	 * @param result The result produced by this <code>Task</code>, if any.
	 */
	protected void finish(R result) {
		for (TaskObserver<R> observer : observers) {
			observer.taskFinished(result);
		}
	}
}

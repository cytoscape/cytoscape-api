package org.cytoscape.work;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 
 * @param <R>
 * 
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule work-api
 */
public abstract class AbstractObservableTask<R> extends AbstractTask implements ObservableTask<R> {
	private Set<TaskObserver<R>> observers;

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
	 * 
	 * @param result
	 */
	protected void finish(R result) {
		for (TaskObserver<R> observer : observers) {
			observer.taskFinished(result);
		}
	}
}

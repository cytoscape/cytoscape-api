package org.cytoscape.work;

/**
 *
 * @param <R>
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule work-api
 */
public interface ObservableTask<R> extends Task {
	/**
	 * 
	 * @param observer
	 */
	void addObserver(TaskObserver<R> observer);
	
	/**
	 * 
	 * @param observer
	 */
	void removeObserver(TaskObserver<R> observer);
}

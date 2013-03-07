package org.cytoscape.work;

/**
 *
 * @param <R>
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule work-api
 */
public interface TaskObserver<R> {
	/**
	 * 
	 * @param result
	 */
	void taskFinished(R result);
}

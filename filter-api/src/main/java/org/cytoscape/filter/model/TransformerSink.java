package org.cytoscape.filter.model;

/**
 * Collects the results of a transformation.  Instances of this interface must
 * be threadsafe.
 * 
 * @param <T> the type of the collected elements.
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule filter-api
 */
public interface TransformerSink<T> {
	/**
	 * Called for each element that is produced from a transformation.
	 * @param element an element produced by a transformation.
	 */
	void collect(T element);
}

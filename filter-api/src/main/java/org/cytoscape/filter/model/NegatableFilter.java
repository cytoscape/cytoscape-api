package org.cytoscape.filter.model;

/**
 * An interface that can be added to a filter that allows the logical result
 * of the filter's apply method to be negated before it is returned.
 *
 * @param <C> The context type of the elements this {@code Filter} operates on.
 * @param <E> The element type this {@code Filter} operates on.
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule filter-api
 */
public interface NegatableFilter {
	
	/**
	 * Returns true if the logical result of the apply() method is negated before being returned.
	 */
	boolean getNegated();
	
	/**
	 * If set to true then the result of the apply() method is negated before being returned.
	 * @param negate
	 */
	void setNegated(boolean negated);

}

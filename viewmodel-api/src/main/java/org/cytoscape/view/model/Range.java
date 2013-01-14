package org.cytoscape.view.model;

/**
 * Defines a range of values for {@link VisualProperty}s.
 *
 * @param <T> The generic type of this Range.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule viewmodel-api
 */
public interface Range<T> {
	

	/**
	 * Type of object used in this range.
	 * 
	 * @return object type.
	 */
	Class<T> getType();
	
	
	/**
	 * If this range is a set of discrete values, return true.
	 * 
	 * @return If discrete, return true.
	 * 
	 */
	boolean isDiscrete();
	
	
	/**
	 * Return true if the given value is in the range defined in this class.
	 * 
	 * @param value any value to be tested.
	 * 
	 * @return true if the given value is in the range.
	 */
	boolean inRange(final T value);
}

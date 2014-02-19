package org.cytoscape.filter.model;

import java.util.List;

/**
 * Provides a sequence of elements from a context.  Instances of this interface
 * must be threadsafe.
 * 
 * @param <C> The context type of the elements this {@code Filter} operates on.
 * @param <E> The element type this {@code Filter} operates on.
 * 
 * @CyAPI.Api.Interface
 * @CyAPI.InModule filter-api
 */
public interface TransformerSource<C, E> {
	/**
	 * Returns a {@code List} of elements from the given context.
	 * @param context the source of the elements. 
	 * @return a {@code List} of elements.
	 */
	List<E> getElementList(C context);

	/**
	 * Returns the type of the context object this {@code TransformerSource} is
	 * compatible with.
	 * @return the type of the context object.
	 */
	Class<C> getContextType();
	
	/**
	 * Returns the type of the elements this {@code TransformerSource} is
	 * compatible with.
	 * @return the type of the elements.
	 */
	Class<E> getElementType();
	
	/**
	 * Returns the number of elements that could be produced from the given
	 * context.
	 * 
	 * @param context the context whose elements should be counted.
	 * @return the number of elements that could be produced from the given
	 *         context.
	 */
	int getElementCount(C context);
}

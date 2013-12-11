package org.cytoscape.filter.model;

import java.util.List;

/**
 * A chain of {@link Transformer}s with a user-defined name.
 * 
 * @param <C> The context type of the elements this {@code NamedTransformer} is compatible with.
 * @param <E> The element type this {@code NamedTransformer} is compatible with.
 * 
 * @CyAPI.Api.Interface
 * @CyAPI.InModule filter-api
 */
public interface NamedTransformer<C, E> {
	/**
	 * Returns the name of this {@code NamedTransformer}.
	 * @return the name.
	 */
	String getName();
	
	/**
	 * Returns the {@code List} of {@code Transformer}s contained within this
	 * {@code NamedTransformer}.
	 *  
	 * @return the {@code List} of {@code Transformer}s contained within.
	 */
	List<Transformer<C, E>> getTransformers();
}

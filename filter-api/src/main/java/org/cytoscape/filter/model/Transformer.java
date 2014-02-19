package org.cytoscape.filter.model;

/**
 * Transforms a stream of elements from a particular context.  The output
 * of a {@code Transformer} must be a subset of the elements of its context.
 * However, the output need not be a subset of its input.
 * 
 * The input and output mechanisms of Transformers are defined by the
 * subinterfaces {@link HolisticTransformer}, {@link ElementTransformer}, and
 * {@link Filter}.
 * 
 * @param <C> The context type of the elements this {@code Transformer} operates on.
 * @param <E> The element type this {@code Transformer} operates on.
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule filter-api
 */
public interface Transformer<C, E> {
	
	/**
	 * Returns the user-friendly display name of this type of
	 * {@code Transformer}.
	 * @return the user-friendly display name of this type of
	 *         {@code Transformer}.
	 */
	String getName();
	
	/**
	 * Returns the unique id of this type of {@code Transformer}.
	 * @return the unique id of this type of {@code Transformer}.
	 */
	String getId();

	/**
	 * Returns the type of the context object this {@code Transformer} is
	 * compatible with.
	 * @return the type of the context object.
	 */
	Class<C> getContextType();
	
	/**
	 * Returns the type of the elements this {@code Transformer} is
	 * compatible with.
	 * @return the type of the elements.
	 */
	Class<E> getElementType();
	
	/**
	 * Subscribes the given listener to changes in this {@code Transformer}'s
	 * parameters.
	 * @param listener the object that should be notified of changes to this
	 * {@code Transformer}.
	 */
	void addListener(TransformerListener listener);
	
	/**
	 * Unsubscribes the given listener from changes in this {@code Transformer}'s
	 * parameters.
	 * @param listener the object that should no longer be notified of changes
	 * to this {@code Transformer}.
	 */
	void removeListener(TransformerListener listener);
}

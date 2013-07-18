package org.cytoscape.filter.model;

/**
 * Transforms a stream of elements from a particular context.  The output
 * of a Transformer must be a subset of the elements of its context.  However,
 * the output need not be a subset of its input.
 * 
 * The input and output mechanisms of Transformers are defined by the
 * subinterfaces HolisticTransformer, ElementTransformer, and Filter.
 */
public interface Transformer<C, E> {
	String getName();
	String getId();

	Class<C> getContextType();
	Class<E> getElementType();
}

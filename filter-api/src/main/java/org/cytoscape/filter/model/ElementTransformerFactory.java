package org.cytoscape.filter.model;

/**
 * A factory for creating {@code ElementTransformer}s.
 * 
 * @param <C> The context type of the elements this factory is compatible with.
 * @param <E> The element type this factory is compatible with.
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule filter-api
 */
public interface ElementTransformerFactory<C, E> extends TransformerFactory<C, E> {
	/**
	 * Returns a new {@code ElementTransformer} with the same id as this
	 * factory.
	 * 
	 * @return a new {@code ElementTransformer}.
	 */
	ElementTransformer<C, E> createElementTransformer();
}

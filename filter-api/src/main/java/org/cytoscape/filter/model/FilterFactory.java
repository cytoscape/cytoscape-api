package org.cytoscape.filter.model;

/**
 * A factory for creating {@code Filter}s.
 * 
 * @param <C> The context type of the elements this factory is compatible with.
 * @param <E> The element type this factory is compatible with.
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule filter-api
 */
public interface FilterFactory<C, E> extends TransformerFactory<C, E> {
	/**
	 * Returns a new {@code Filter} with the same id as this factory.
	 * 
	 * @return a new {@code Filter}.
	 */
	Filter<C, E> createFilter();
}

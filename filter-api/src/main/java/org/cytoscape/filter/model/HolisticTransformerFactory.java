package org.cytoscape.filter.model;

/**
 * A factory for creating {@code HolisticTransformer}s.
 * 
 * @param <C> The context type of the elements this factory is compatible with.
 * @param <E> The element type this factory is compatible with.
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule filter-api
 */
public interface HolisticTransformerFactory<C, E> extends TransformerFactory<C, E> {
	HolisticTransformer<C, E> createHolisticTransformer();
}

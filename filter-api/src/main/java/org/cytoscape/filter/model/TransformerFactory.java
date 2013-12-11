package org.cytoscape.filter.model;

/**
 * The base interface for all {@code Transformer} factories.  The subinterfaces
 * {@code FilterFactory}, {@code ElementTransformerFactory}, or
 * {@code HolisticTransformerFactory} should be implemented instead of this one.
 *
 * @param <C> The context type of the elements this factory is compatible with.
 * @param <E> The element type this factory is compatible with.
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule filter-api
 */
public interface TransformerFactory<C, E> {
	/**
	 * Returns the unique id of the {@code Transformer} produced by this factory.
	 * @return the unique id of the {@code Transformer} produced by this factory.
	 */
	String getId();
}

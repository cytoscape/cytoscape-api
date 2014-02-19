package org.cytoscape.filter.model;

/**
 * A transformer that decides whether to accept or reject elements.
 * 
 * To make a custom {@code Filter}
 * available in Cytoscape, create an {@link FilterFactory} that
 * constructs the custom {@code Filter}, and register the factory
 * as an OSGi service.  To make the transformer show up in the UI, create a
 * {@link org.cytoscape.filter.view.TransformerViewFactory TransformerViewFactory}
 * and register it as an OSGi service.  The
 * {@link Filter}, {@link FilterFactory} and
 * {@link org.cytoscape.filter.view.TransformerViewFactory TransformerViewFactory}
 * need to have the same id.
 * 
 * @param <C> The context type of the elements this {@code Filter} operates on.
 * @param <E> The element type this {@code Filter} operates on.
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule filter-api
 */
public interface Filter<C, E> extends Transformer<C, E> {
	/**
	 * Returns {@code true} if this filter accepts the given element from
	 * {@code context}.
	 * 
	 * @param context the context object of the transformed element.
	 * @param element the element to transform.
	 * @return {@code true} if this filter accepts the given element.
	 */
	boolean accepts(C context, E element);
}

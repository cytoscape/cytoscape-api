package org.cytoscape.filter.model;


/**
 * A transformer that transforms its input one element at a time and pushes
 * the results into the given sink.
 * 
 * To make a custom {@code ElementTransformer}
 * available in Cytoscape, create an {@link ElementTransformerFactory} that
 * constructs the custom {@code ElementTransformer}, and register the factory
 * as an OSGi service.  To make the transformer show up in the UI, create a
 * {@link org.cytoscape.filter.view.TransformerViewFactory TransformerViewFactory}
 * and register it as an OSGi service.  The
 * {@link ElementTransformer}, {@link ElementTransformerFactory} and
 * {@link org.cytoscape.filter.view.TransformerViewFactory TransformerViewFactory}
 * need to have the same id.
 * 
 * @param <C> The context type of the elements this {code ElementTransformer} operates on.
 * @param <E> The element type this {code ElementTransformer} operates on.
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule filter-api
 */
public interface ElementTransformer<C, E> extends Transformer<C, E> {
	/**
	 * Transforms the given {@code element}. The results of the
	 * transformation are collected by {@code sink}.
	 * 
	 * @param context the context object of the transformed element.
	 * @param element the element to transform.
	 * @param sink collects the results of the transformation.
	 */
	void apply(C context, E element, TransformerSink<E> sink);
}

package org.cytoscape.filter.model;


/**
 * A transformer that takes its input altogether, transforms it, and pushes
 * the results into the given sink.
 * 
 * To make a custom {@code HolisticTransformer}
 * available in Cytoscape, create an {@link HolisticTransformerFactory} that
 * constructs the custom {@code HolisticTransformer}, and register the factory
 * as an OSGi service.  To make the transformer show up in the UI, create a
 * {@link org.cytoscape.filter.view.TransformerViewFactory TransformerViewFactory}
 * and register it as an OSGi service.  The
 * {@link HolisticTransformer}, {@link HolisticTransformerFactory} and
 * {@link org.cytoscape.filter.view.TransformerViewFactory TransformerViewFactory}
 * need to have the same id.
 * 
 * @param <C> The context type of the elements this {@code HolisticTransformer} operates on.
 * @param <E> The element type this {@code HolisticTransformer} operates on.
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule filter-api
 */
public interface HolisticTransformer<C, E> extends Transformer<C, E> {
	/**
	 * Transforms the elements in {@code source} from {@code context}. The
	 * results of the transformation are collected by {@code sink}.
	 * 
	 * @param context the context object of the transformed element.
	 * @param source provides the elements to transform.
	 * @param sink collects the results of the transformation.
	 */
	void apply(C context, TransformerSource<C, E> source, TransformerSink<E> sink);
}

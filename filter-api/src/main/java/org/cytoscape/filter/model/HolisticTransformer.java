package org.cytoscape.filter.model;


/**
 * A transformer that takes its input altogether, transforms it, and pushes
 * the results into the given sink.
 */
public interface HolisticTransformer<C, E> extends Transformer<C, E> {
	void apply(C context, TransformerSource<C, E> source, TransformerSink<E> sink);
}

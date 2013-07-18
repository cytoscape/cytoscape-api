package org.cytoscape.filter.model;


/**
 * A transformer that transforms its input one element at a time and pushes
 * the results into the given sink.
 */
public interface ElementTransformer<C, E> extends Transformer<C, E> {
	void apply(C context, E element, TransformerSink<E> sink);
}

package org.cytoscape.filter.model;

/**
 * A transformer that decides whether to accept or reject elements.
 */
public interface Filter<C, E> extends Transformer<C, E> {
	boolean accepts(C context, E element);
}

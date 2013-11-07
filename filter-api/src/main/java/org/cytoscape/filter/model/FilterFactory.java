package org.cytoscape.filter.model;

public interface FilterFactory<C, E> extends TransformerFactory<C, E> {
	Filter<C, E> createFilter();
}

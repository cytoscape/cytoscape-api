package org.cytoscape.filter.model;

public interface ElementTransformerFactory<C, E> extends TransformerFactory<C, E> {
	ElementTransformer<C, E> createElementTransformer();
}

package org.cytoscape.filter.model;

public interface TransformerFactory<C, E> {
	Transformer<C, E> createTransformer();
	String getId();
}

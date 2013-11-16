package org.cytoscape.filter.model;

import java.util.List;

public interface NamedTransformer<C, E> {
	String getName();
	List<Transformer<C, E>> getTransformers();
}

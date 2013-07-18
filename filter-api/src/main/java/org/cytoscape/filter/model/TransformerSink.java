package org.cytoscape.filter.model;

public interface TransformerSink<T> {
	void collect(T element);
}

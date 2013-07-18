package org.cytoscape.filter.model;

import java.util.List;

public interface TransformerExecutionStrategy {
	<C, E> void execute(C context, List<Transformer<C, E>> transformers, TransformerSource<C, E> source, TransformerSink<E> sink);
}

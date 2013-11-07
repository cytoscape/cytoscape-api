package org.cytoscape.filter;

import java.util.List;

import org.cytoscape.filter.model.CompositeFilter;
import org.cytoscape.filter.model.Transformer;
import org.cytoscape.filter.model.TransformerSink;
import org.cytoscape.filter.model.TransformerSource;

public interface TransformerManager {
	<C, E> CompositeFilter<C, E> createCompositeFilter(Class<C> contextType, Class<E> elementType);
	<C, E> TransformerSource<C, E> getTransformerSource(Class<C> context);
	<C, E> Transformer<C, E> createTransformer(String id);

	<C, E> void execute(C context, Transformer<C, E> transformer, TransformerSink<E> sink);
	<C, E> void execute(C context, List<Transformer<C, E>> transformers, TransformerSink<E> sink);
	<C, E> void execute(C context, TransformerSource<C, E> source, List<Transformer<C, E>> transformers, TransformerSink<E> sink);
}

package org.cytoscape.filter;

import java.util.List;

import org.cytoscape.filter.model.CompositeFilter;
import org.cytoscape.filter.model.Transformer;
import org.cytoscape.filter.model.TransformerSink;
import org.cytoscape.filter.model.TransformerSource;

/**
 * A class for creating and executing Transformers.
 * 
 * @CyAPI.Api.Interface
 * @CyAPI.InModule filter-api
 */
public interface TransformerManager {
	/**
	 * Returns a new empty {@code CompositeFilter} that selects elements of type
	 * {@code elementType} from an object of type {@code contextType}.
	 * 
	 * @param contextType The type of the context this filter selects from.
	 * @param elementType The type of the elements this filter selects.
	 * @return a new empty {@code CompositeFilter}.
	 */
	<C, E> CompositeFilter<C, E> createCompositeFilter(Class<C> contextType, Class<E> elementType);

	/**
	 * Returns a {@code TransformerSource} for the given contextType.
	 * 
	 * @param contextType The type of the context object the TransformerSource
	 *                    should produce an element stream for.
	 * @return a TransformerSource for the given context object type.
	 */
	<C, E> TransformerSource<C, E> getTransformerSource(Class<C> contextType);

	/**
	 * Returns a new Transformer with the given {@code id}.  See {@link org.cytoscape.filter.transformers.Transformers Transformers}
	 * for a list of the ids of the transformers that are part of the Cytoscape
	 * core.
	 * 
	 * @param id the unique id of the type of Transformer to create. 
	 * @return a new Transformer with the given {@code id}.
	 */
	<C, E> Transformer<C, E> createTransformer(String id);

	/**
	 * Applies {@code transformer} to the {@code context}.  The results
	 * of the transformation are collected by {@code sink}.
	 * 
	 * @param context the object whose elements should be transformed.
	 * @param transformer the {@code Transformer} that should be applied.
	 * @param sink collects the results of the transformation.
	 */
	<C, E> void execute(C context, Transformer<C, E> transformer, TransformerSink<E> sink);

	/**
	 * Applies the given chain of {@code transformers} to the {@code context}.
	 * The results of the transformation are collected by {@code sink}.
	 * 
	 * @param context the object whose elements should be transformed.
	 * @param transformer the {@code Transformer}s that should be applied.
	 * @param sink collects the results of the transformation.
	 */
	<C, E> void execute(C context, List<Transformer<C, E>> transformers, TransformerSink<E> sink);

	/**
	 * Applies the given chain of {@code transformers} to the {@code context}, via
	 * {@code source}.  The {@code source} determines which elements are passed to the
	 * {@code transformers}.  The results of the transformation are collected by {@code sink}.
	 * 
	 * @param context the object whose elements should be transformed.
	 * @param source determines which elements are taken from the context.
	 * @param transformer the {@code Transformer}s that should be applied.
	 * @param sink collects the results of the transformation.
	 */
	<C, E> void execute(C context, TransformerSource<C, E> source, List<Transformer<C, E>> transformers, TransformerSink<E> sink);
}

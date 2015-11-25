package org.cytoscape.filter.model;

/**
 * A {@link Transformer} that contains a {@link CompositeFilter} that it uses to filter its output.
 * <br><br>
 * This interface is typically added to an {@link ElementTransformer} or {@link HolisticTransformer}
 * to indicate that it contains sub-filters. 
 *
 * @param <C> The context type of the elements this {@code SubFilterTransformer} operates on.
 * @param <E> The element type this {@code SubFilterTransformer} operates on.
 */
public interface SubFilterTransformer<C,E> extends Transformer<C,E> {
	
	public CompositeFilter<C,E> getCompositeFilter();
	
}

package org.cytoscape.filter.model;

/**
 * A {link Filter} that computes the set-union or set-intersection of the
 * {link Filter}s it contains.  The ordering of the child filters does not
 * affect the final output of this filter, but may impact performance.
 * 
 * @param <C> The context type of the elements this {@link Filter} operates on.
 * @param <E> The element type this {@link Filter} operates on.
 * 
 * @CyAPI.Api.Interface
 * @CyAPI.InModule filter-api
 */
public interface CompositeFilter<C, E> extends Filter<C, E> {
	
	/**
	 * Determines how a {@code CompositeFilter} combines the results of its
	 * contained {link Filter}s.
	 */
	enum Type {
		/**
		 * Use set-union semantics (logical OR) to combine the results.
		 */
		ANY,
		
		/**
		 * Use set-intersection semantics (logical AND) to combine the results.
		 */
		ALL
	}
	
	/**
	 * Appends {@code filter} to the list of children.
	 * @param filter the {@code Filter} to add.
	 */
	void append(Filter<C, E> filter);
	
	/**
	 * Inserts {@code filter} at the given index within the list of children.
	 * @param index the index where the filter should be inserted.
	 * @param filter the {@code Filter} to add.
	 */
	void insert(int index, Filter<C, E> filter);
	
	/**
	 * Returns the {@code Filter} at the given index.
	 * @param index the index of the {@code Filter} to get.
	 * @return the {@code Filter} at the given index.
	 */
	Filter<C, E> get(int index);
	
	/**
	 * Removes the {@code Filter} at the given index from the list of children
	 * and returns in.
	 * @param index the index of the {@code Filter} to get.
	 * @return the {@code Filter} at the given index.
	 */
	Filter<C, E> remove(int index);
	
	/**
	 * Returns the index of {@code filter} within the list of children,
	 * or {@code -1} if {@code filter} is not in that list.
	 * 
	 * @param filter the {@code filter} whose index should be looked up.
	 * @return the index of {@code filter} or {@code -1} if it cannot be found.
	 */
	int indexOf(Filter<C, E> filter);
	
	/**
	 * Returns the number of direct children {@code Filter}s contained by
	 * this {@code CompositeFilter}.
	 * @return the number of direct children.
	 */
	int getLength();
	
	/**
	 * Returns the type of combining semantics used by this filter.
	 * @return the type of combining semantics used by this filter.
	 */
	Type getType();
	
	/**
	 * Sets the type of combining semantics used by this filter.
	 * @param type the combining semantics to use.
	 */
	void setType(Type type);
}

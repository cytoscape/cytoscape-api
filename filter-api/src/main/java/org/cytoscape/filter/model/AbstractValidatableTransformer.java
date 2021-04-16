package org.cytoscape.filter.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * A convenience base class for ValidatableTransformers.  This class provides a thread-safe
 * implementation of listener registration, removal and notification.
 * 
 * @param <C> The context type of the elements this {@link Transformer} operates on.
 * @param <E> The element type this {@link Transformer} operates on.
 * 
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule filter-api
 */
public abstract class AbstractValidatableTransformer<C, E> extends AbstractTransformer<C,E> implements ValidatableTransformer<C, E> {
	
	/**
	 * Creates a new AbstractValidatableTransformer.
	 */
	public AbstractValidatableTransformer() {
		super();
	}
}

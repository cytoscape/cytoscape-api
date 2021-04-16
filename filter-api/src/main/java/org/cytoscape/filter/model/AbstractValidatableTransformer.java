package org.cytoscape.filter.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * A convenience base class for Transformers.  This class provides a thread-safe
 * implementation of listener registration, removal and notification.
 * 
 * @param <C> The context type of the elements this {@link Transformer} operates on.
 * @param <E> The element type this {@link Transformer} operates on.
 * 
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule filter-api
 */
public abstract class AbstractValidatableTransformer<C, E> implements ValidatableTransformer<C, E> {
	private List<TransformerListener> listeners;
	
	/**
	 * Creates a new AbstractTransformer.
	 */
	public AbstractValidatableTransformer() {
		listeners = new CopyOnWriteArrayList<TransformerListener>();
	}
	
	@Override
	public final void addListener(TransformerListener listener) {
		if (listeners.contains(listener)) {
			return;
		}
		listeners.add(listener);
	}
	
	@Override
	public final void removeListener(TransformerListener listener) {
		listeners.remove(listener);
	}
	
	/**
	 * Notifies any registered listeners that this transformer's settings
	 * have changed.
	 */
	protected final void notifyListeners() {
		for (TransformerListener listener : listeners) {
			listener.handleSettingsChanged();
		}
	}
}

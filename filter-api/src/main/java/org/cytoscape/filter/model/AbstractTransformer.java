package org.cytoscape.filter.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AbstractTransformer<C, E> implements Transformer<C, E> {
	List<TransformerListener> listeners;
	
	public AbstractTransformer() {
		listeners = new CopyOnWriteArrayList<TransformerListener>();
	}
	
	@Override
	public void addListener(TransformerListener listener) {
		if (listeners.contains(listener)) {
			return;
		}
		listeners.add(listener);
	}
	
	@Override
	public void removeListener(TransformerListener listener) {
		listeners.remove(listener);
	}
	
	protected void notifyListeners() {
		for (TransformerListener listener : listeners) {
			listener.handleSettingsChanged();
		}
	}
}

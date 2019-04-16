package org.cytoscape.view.model;

import java.util.function.Predicate;

public interface CyNetworkViewConfig {
	
	public static final Object SELECTED_NODES = new Object();
	public static final Object SELECTED_EDGES = new Object();
	
	<T, V extends T> void addTrackedVisualProperty(Object key, VisualProperty<? extends T> vp, Predicate<V> valueTester);

	default <T, V extends T> void addTrackedVisualProperty(Object key, VisualProperty<? extends T> vp, V value) {
		addTrackedVisualProperty(key, vp, value::equals);
	}
	
	public void addNonClearableVisualProperty(VisualProperty<?> vp);
}

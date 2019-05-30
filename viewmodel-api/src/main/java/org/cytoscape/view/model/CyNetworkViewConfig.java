package org.cytoscape.view.model;

import java.util.function.Predicate;


/**
 * Used to configure the behavior of {@link CyNetworkView} objects created using
 * the {@link CyNetworkViewFactoryFactory} service.
 *
 * @CyAPI.Api.Interface
 * @CyAPI.InModule viewmodel-api
 */
public interface CyNetworkViewConfig {
	
	/**
	 * Key used to get a list of selected nodes from the 
	 * {@link CyNetworkViewSnapshot#getTrackedNodes(Object)} method.
	 */
	public static final Object SELECTED_NODES = "SELECTED_NODES";
	
	/**
	 * Key used to get a list of selected edges from the 
	 * {@link CyNetworkViewSnapshot#getTrackedEdges(Object)} method.
	 */
	public static final Object SELECTED_EDGES = "SELECTED_EDGES";
	
	
	/**
	 * Turns on tracking of the given VisualProperty such that nodes/edge that have the VisualProperty set 
	 * to a value that satisfies the given Predicate are returned by the 
	 * {@link CyNetworkViewSnapshot#getTrackedNodes(Object)} or 
	 * {@link CyNetworkViewSnapshot#getTrackedEdges(Object)} method .
	 * 
	 * @param <T> Data type of the visual property.  This can be subclasses of type T.
	 * @param <V> Value of the visual property.  This can be subclasses of T. 
	 * 
	 * @param key Key object used to retrieve tracked nodes/edges from 
	 *   {@link CyNetworkViewSnapshot#getTrackedNodes(Object)} or {@link CyNetworkViewSnapshot#getTrackedEdges(Object)}
	 * @param vp The VisualProperty to track.
	 * @param valueTester Predicate nodes/edges that satisfy this predicate are tracked
	 */
	<T, V extends T> void addTrackedVisualProperty(Object key, VisualProperty<? extends T> vp, Predicate<V> valueTester);
	
	/**
	 * Turns on tracking of the given VisualProperty such that nodes/edge that have the VisualProperty set 
	 * to a value that is equal to the given value are returned by the 
	 * {@link CyNetworkViewSnapshot#getTrackedNodes(Object)} or 
	 * {@link CyNetworkViewSnapshot#getTrackedEdges(Object)} method .
	 * 
	 * @param <T> Data type of the visual property.  This can be subclasses of type T.
	 * @param <V> Value of the visual property.  This can be subclasses of T. 
	 * 
	 * @param key Key object used to retrieve tracked nodes/edges from 
	 *   {@link CyNetworkViewSnapshot#getTrackedNodes(Object)} or {@link CyNetworkViewSnapshot#getTrackedEdges(Object)}
	 * @param vp The VisualProperty to track.
	 * @param value Visual property values that are equal to this value are tracked.
	 */
	default <T, V extends T> void addTrackedVisualProperty(Object key, VisualProperty<? extends T> vp, V value) {
		addTrackedVisualProperty(key, vp, value::equals);
	}
	
	/**
	 * Add a VisualProperty that will not have its value cleared when 
	 * {@link View#clearVisualProperties()} is called. This is typically used with node
	 * coordinates (ie X and Y position) so that the network layout isn't lost when VisualProperties
	 * are cleared.
	 */
	public void addNonClearableVisualProperty(VisualProperty<?> vp);
}

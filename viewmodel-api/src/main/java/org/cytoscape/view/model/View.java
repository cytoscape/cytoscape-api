package org.cytoscape.view.model;

import java.util.function.Consumer;

import org.cytoscape.model.CyIdentifiable;

/**
 * The base interface that defines methods used to set visual properties
 * for nodes, edges, and networks.
 *
 * Think of it as a row in the view-model table.
 *
 * @param <S> the base (model-level) object for which this is a View. For example, CyNode or CyEdge.
 * In the future versions, Attributes can be this parameter.
 * @CyAPI.Api.Interface 
 * @CyAPI.InModule viewmodel-api
 */
public interface View<S> extends CyIdentifiable {
	
	/**
	 * Get the actual value for the given visual property.
	 * 
	 * @param <T> Object type of the Visual Property (Color, Double, ...)
	 * 
	 * @param vp the VisualProperty.
	 * @return Value associated with this View and Visual Property pair.
	 */
	<T> T getVisualProperty(VisualProperty<T> vp);

	/**
	 * Indicates whether or not the passed visual property is set with a non-null value.
	 * @param vp the VisualProperty.
	 * @return true if the view has a non-null value for the VisualProperty.
	 */
	boolean isSet(VisualProperty<?> vp);

	/**
	 * Returns true if the value of the given VisualProperty or one of its
	 * ancestors is locked.
	 * @param vp the VisualProperty
	 * @return true if value of the given VisualProperty value or one of its
	 * ancestors is locked.
	 */
	boolean isValueLocked(VisualProperty<?> vp);

	/**
	 * Returns true if the given VisualProperty's value has been locked.
	 * directly.
	 * @param vp the VisualProperty
	 * @return true if the given VisualProperty's value has been locked.
	 */
	boolean isDirectlyLocked(VisualProperty<?> vp);
	/**
	 * Assign a value to the given Visual Property of this view.
	 * 
	 * @param <T> Data type of the visual property.  This can be subclasses of type T.
	 * @param <V> Value of the visual property.  This can be subclasses of T. 
	 * 
	 * @param vp the VisualProperty (Node Color, Edge Width, etc.)
	 * @param value actual value stored in this visual property.
	 */
	<T, V extends T> void setVisualProperty(VisualProperty<? extends T> vp, V value);
	

	/**
	 * Set locked value.  This value will be used to bypass the style.
	 * 
	 * @param <T> Data type of the visual property.  This can be subclasses of type T.
	 * @param <V> Value of the visual property.  This can be subclasses of T. 
	 * @param vp the VisualProperty
	 * @param value the value that will bypass the style
	 */
	<T, V extends T> void setLockedValue(VisualProperty<? extends T> vp, V value);

	
	/**
	 * Clear value lock for given VisualProperty.
	 *
	 * @param vp the VisualProperty.
	 */
	void clearValueLock(VisualProperty<?> vp);

	
	/**
	 *  Get source data structure, such as CyNode, CyEdge, etc.
	 *
	 * @return Data object of this view.
	 */
	S getModel();
	
	/**
	 * Clear all VisualProperty values previously set to this view.
	 */
	void clearVisualProperties();
	
	
	/**
	 * Performs the given action on this View object. Any synchronization resources, 
	 * such as locks, are acquired at the start of the action and held until the
	 * action is complete.
	 * 
	 * If this network view supports creating snapshots then the dirty flag is not
	 * set until the action is complete.
	 * 
	 * <p>
	 * The default implementation of this method assumes there are no resources to acquire.
	 * </p>
	 * 
	 * @param viewAction The action to be performed once on this view.
	 * @param setDirty If false then the dirty flag will not be set when the action completes.
	 * 
	 * @see CyNetworkView#isDirty()
	 */
	default void batch(Consumer<View<S>> viewAction, boolean setDirty) {
		viewAction.accept(this);
	}
	
	/**
	 * Performs the given action on this View object. Any synchronization resources, 
	 * such as locks, are acquired at the start of the action and held until the
	 * action is complete.
	 * 
	 * If this network view supports creating snapshots then the dirty flag is not
	 * set until the action is complete.
	 * 
	 * @param viewAction The action to be performed once on this view.
	 * 
	 * @see CyNetworkView#isDirty()
	 */
	default void batch(Consumer<View<S>> viewAction) {
		batch(viewAction, true);
	}
	
}

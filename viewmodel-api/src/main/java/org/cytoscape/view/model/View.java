package org.cytoscape.view.model;

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
public interface View<S> extends ReadableView<S> {
	
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
	
}

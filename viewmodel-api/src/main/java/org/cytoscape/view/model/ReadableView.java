package org.cytoscape.view.model;

import org.cytoscape.model.CyIdentifiable;

public interface ReadableView<S> extends CyIdentifiable {

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

}
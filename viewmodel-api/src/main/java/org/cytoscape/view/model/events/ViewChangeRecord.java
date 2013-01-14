package org.cytoscape.view.model.events;

import org.cytoscape.view.model.View;
import org.cytoscape.view.model.VisualProperty;

/**
 * A utility class used to describe one change to a {@link View}. 
 * @CyAPI.Final.Class
 * @CyAPI.InModule viewmodel-api
 */
public final class ViewChangeRecord<T> {
	private final View<T> view;
	private final VisualProperty<?> vp;
	private final Object value;

	/**
	 * Constructor.
	 * @param view The View that has changed.
	 * @param vp The VisualProperty that has changed.
	 * @param value The value to which the visual property has been set.
	 */
	public ViewChangeRecord(View<T> view, VisualProperty<?> vp, Object value) {
		if ( view == null || vp == null )
			throw new NullPointerException("View and/or VisualProperty may not be null");
		this.view = view;
		this.vp = vp;
		this.value = value;
	}

	/**
	 * Returns the view that has been changed.
	 * @return the view that has been changed.
	 */
	public View<T> getView() {
		return view;
	}
	
	/**
	 * Returns the visual property that has been changed.
	 * @return the visual property that has been changed.
	 */
	public VisualProperty<?> getVisualProperty() {
		return vp;
	}
	
	/**
	 * Returns the value that the visual property has been changed to.
	 * @return the value that the visual property has been changed to.
	 */
	public Object getValue() {
		return value;
	}
}

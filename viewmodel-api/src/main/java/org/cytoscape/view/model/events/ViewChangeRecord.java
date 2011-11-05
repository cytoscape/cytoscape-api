package org.cytoscape.view.model.events;

import org.cytoscape.view.model.View;
import org.cytoscape.view.model.VisualProperty;

/**
 * #ASKMIKE Needs class description and method descriptions.
 * @CyAPI.Final.Class
 */
public final class ViewChangeRecord<T> {
	private final View<T> view;
	private final VisualProperty<?> vp;
	private final Object value;

	public ViewChangeRecord(View<T> view, VisualProperty<?> vp, Object value) {
		if ( view == null || vp == null )
			throw new NullPointerException("View and/or VisualProperty may not be null");
		this.view = view;
		this.vp = vp;
		this.value = value;
	}
	
	public View<T> getView() {
		return view;
	}
	
	public VisualProperty<?> getVisualProperty() {
		return vp;
	}
	
	public Object getValue() {
		return value;
	}
}

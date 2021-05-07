package org.cytoscape.view.model.events;

/*
 * #%L
 * Cytoscape View Model API (viewmodel-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2021 The Cytoscape Consortium
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

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
	private final boolean lockedValue;

	/**
	 * Constructor.
	 * @param view The View that has changed.
	 * @param vp The VisualProperty that has changed.
	 * @param value The value to which the visual property has been set.
	 */
	public ViewChangeRecord(View<T> view, VisualProperty<?> vp, Object value) {
		this(view, vp, value, false);
	}
	
	public ViewChangeRecord(View<T> view, VisualProperty<?> vp, Object value, boolean lockedValue) {
		if ( view == null || vp == null )
			throw new NullPointerException("View and/or VisualProperty may not be null");
		this.view = view;
		this.vp = vp;
		this.value = value;
		this.lockedValue = lockedValue;
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
	
	public boolean isLockedValue() {
		return lockedValue;
	}
}

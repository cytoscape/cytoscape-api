package org.cytoscape.view.model.events;

/*
 * #%L
 * Cytoscape View Model API (viewmodel-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
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

import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.view.model.View;
import org.cytoscape.view.model.VisualProperty;

public final class LockedValueSetRecord {
	
	private final View<? extends CyIdentifiable> view;
	private final VisualProperty<?> visualProperty;
	private final Object value;
	
	public LockedValueSetRecord(final View<? extends CyIdentifiable> view,
								final VisualProperty<?> visualProperty,
								final Object value) {
		if (view == null)
			throw new IllegalArgumentException("'view' must not be null.");
		if (visualProperty == null)
			throw new IllegalArgumentException("'visualProperty' must not be null.");
		
		this.view = view;
		this.visualProperty = visualProperty;
		this.value = value;
	}
	
	public View<? extends CyIdentifiable> getView() {
		return view;
	}
	
	public VisualProperty<?> getVisualProperty() {
		return visualProperty;
	}
	
	public Object getValue() {
		return value;
	}
}

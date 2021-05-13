package org.cytoscape.view.presentation.property.values;

/*
 * #%L
 * Cytoscape Presentation API (presentation-api)
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


/**
 * Base class for all {@link VisualPropertyValue}.
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule presentation-api
 */
public abstract class AbstractVisualPropertyValue implements VisualPropertyValue {

	private final String displayName;
	private final String serializableString;
	
	/**
	 * Constructs an AbstractVisualPropertyValue.
	 * @param displayName the display name of the visual property value.
	 * @param serializableString the serialiable string of the visual property value
	 */
	public AbstractVisualPropertyValue(final String displayName, final String serializableString) {
		this.displayName = displayName;
		this.serializableString = serializableString;
	}
	
	@Override
	public String getDisplayName() {
		return this.displayName;
	}

	@Override
	public String getSerializableString() {
		return this.serializableString;
	}
	
	@Override
	public String toString() {
		return displayName;
	}

}

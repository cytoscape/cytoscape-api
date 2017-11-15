package org.cytoscape.view.presentation.property;

/*
 * #%L
 * Cytoscape Presentation API (presentation-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2013 The Cytoscape Consortium
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
import org.cytoscape.view.model.AbstractVisualProperty;
import org.cytoscape.view.model.Range;

/**
 * Visual Property for {@link Integer} values.
 * 
 * @CyAPI.Final.Class
 * @CyAPI.InModule presentation-api
 */
public final class IntegerVisualProperty extends AbstractVisualProperty<Integer> {

	/**
	 * Constructor.
	 * @param def The default Integer value.
	 * @param range The allowable range for Integer values of this visual property. 
	 * @param id A machine readable string identifying this visual property used for XML serialization. 
	 * @param displayName A human readable string used for displays and user interfaces. 
	 * @param modelDataType The model data type associated with this visual property, e.g. CyNode, CyEdge, or CyNetwork. 
	 */
	public IntegerVisualProperty(final Integer def, final Range<Integer> range, final String id, final String displayName, final Class<? extends CyIdentifiable> modelDataType) {
		super(def, range, id, displayName, modelDataType);
	}

	
	@Override
	public String toSerializableString(final Integer value) {
	try
	{
		return value.toString();
	}
	catch (ClassCastException ex)
	{
		System.err.println("ClassCast: " + value);
		ex.printStackTrace();
		return "";
	}
	}

	
	@Override
	public Integer parseSerializableString(final String text) {
		// Cytoscape 2.x serializes integer attributes as decimals (e.g."1.0")!
		return Double.valueOf(text).intValue();
	}
}

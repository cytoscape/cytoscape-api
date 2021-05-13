package org.cytoscape.view.presentation.property;

/*
 * #%L
 * Cytoscape Presentation API (presentation-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2021 The Cytoscape Consortium
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
 * Visual Property for {@link Double} values. 
 * 
 * @CyAPI.Final.Class
 * @CyAPI.InModule presentation-api
 */
public final class DoubleVisualProperty extends AbstractVisualProperty<Double> {

	/**
	 * Constructor.
	 * @param def The double value. 
	 * @param range The allowable range of values for specified doubles.
	 * @param id A machine readable string identifying this visual property used for XML serialization. 
	 * @param displayName A human readable string used for displays and user interfaces. 
	 * @param modelDataType The model data type associated with this visual property, e.g. CyNode, CyEdge, or CyNetwork. 
	 */
	public DoubleVisualProperty(final Double def, final Range<Double> range, final String id, final String displayName, final Class<? extends CyIdentifiable> modelDataType) {
		this(def, range, id, displayName, false, modelDataType);
	}

	/**
	 * Constructor.
	 * @param def The double value. 
	 * @param range The allowable range of values for specified doubles.
	 * @param id A machine readable string identifying this visual property used for XML serialization. 
	 * @param displayName A human readable string used for displays and user interfaces. 
	 * @param ignoreDefault Whether the default value should be ignored. 
	 * @param modelDataType The model data type associated with this visual property, e.g. CyNode, CyEdge, or CyNetwork. 
	 */
	public DoubleVisualProperty(final Double def, final Range<Double> range, final String id,
			final String displayName, final boolean ignoreDefault, final Class<? extends CyIdentifiable> modelDataType) {
		super(def, range, id, displayName, modelDataType);
		this.shouldIgnoreDefault = ignoreDefault;
	}

	@Override
	public String toSerializableString(final Double value) {
		return value.toString();
	}

	@Override
	public Double parseSerializableString(final String text) {
		return Double.valueOf(text);
	}
}

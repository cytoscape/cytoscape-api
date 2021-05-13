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


import java.util.HashSet;
import java.util.Set;

import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.view.model.AbstractVisualProperty;
import org.cytoscape.view.model.DiscreteRange;
import org.cytoscape.view.model.Range;

/**
 * Visual Property for {@link Boolean} values.
 * 
 * @CyAPI.Final.Class
 * @CyAPI.InModule presentation-api
 */
public final class BooleanVisualProperty extends AbstractVisualProperty<Boolean> { 
	
	private static final Range<Boolean> BOOLEAN_RANGE;
	
	static {
		final Set<Boolean> bRange = new HashSet<Boolean>();
		bRange.add(true);
		bRange.add(false);
		BOOLEAN_RANGE = new DiscreteRange<Boolean>(Boolean.class, bRange); 
	}
	
	/**
	 * Constructor.
	 * @param def The boolean value. 
	 * @param id A machine readable string identifying this visual property used for XML serialization. 
	 * @param displayName A human readable string used for displays and user interfaces. 
	 * @param modelDataType The model data type associated with this visual property, e.g. CyNode, CyEdge, or CyNetwork. 
	 */
	public BooleanVisualProperty(final Boolean def, final String id, final String displayName, final Class<? extends CyIdentifiable> modelDataType) {
		this(def, id, displayName, false, modelDataType);
	}
	
	/**
	 * Constructor.
	 * @param def The boolean value. 
	 * @param id A machine readable string identifying this visual property used for XML serialization. 
	 * @param displayName A human readable string used for displays and user interfaces. 
	 * @param ignoreDefault Whether the default value should be ignored. 
	 * @param modelDataType The model data type associated with this visual property, e.g. CyNode, CyEdge, or CyNetwork. 
	 */
	public BooleanVisualProperty(final Boolean def, final String id, final String displayName, final Boolean ignoreDefault, final Class<? extends CyIdentifiable> modelDataType) {
		super(def, BOOLEAN_RANGE, id, displayName, modelDataType);
		this.shouldIgnoreDefault = ignoreDefault;
	}
	
	
	@Override
	public String toSerializableString(final Boolean value) {
		return value.toString();
	}

	@Override
	public Boolean parseSerializableString(final String text) {
		return Boolean.valueOf(text);
	}
}

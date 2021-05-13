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
 * Visual Property for {@link String} values.
 * 
 * @CyAPI.Final.Class
 * @CyAPI.InModule presentation-api
 */
public final class StringVisualProperty extends AbstractVisualProperty<String> {

	/**
	 * Constructor.
	 * @param def The default String value.
	 * @param range The allowable range of Strings for this visual property.
	 * @param id A machine readable string identifying this visual property used for XML serialization. 
	 * @param displayName A human readable string used for displays and user interfaces. 
	 * @param modelDataType The model data type associated with this visual property, e.g. CyNode, CyEdge, or CyNetwork. 
	 */
	public StringVisualProperty(final String def, final Range<String> range, final String id, final String displayName,
			final Class<? extends CyIdentifiable> modelDataType) {
		super(def, range, id, displayName, modelDataType);
	}
	
	/**
	 * Constructor.
	 * @param def The default String value.
	 * @param range The allowable range of Strings for this visual property.
	 * @param id A machine readable string identifying this visual property used for XML serialization. 
	 * @param displayName A human readable string used for displays and user interfaces. 
	 * @param ignoreDefault Whether the default value should be ignored or not. 
	 * @param modelDataType The model data type associated with this visual property, e.g. CyNode, CyEdge, or CyNetwork. 
	 */
	public StringVisualProperty(final String def, final Range<String> range, final String id, final String displayName,
			final boolean ignoreDefault, final Class<? extends CyIdentifiable> modelDataType) {
		super(def, range, id, displayName, modelDataType);
		this.shouldIgnoreDefault = ignoreDefault;
	}

	@Override
	public String toSerializableString(final String value) {
		return value;
	}

	@Override
	public String parseSerializableString(final String text) {
		return text;
	}
}

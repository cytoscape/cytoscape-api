package org.cytoscape.view.presentation.property.table;

import java.util.HashSet;

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
import org.cytoscape.view.model.DiscreteRange;
import org.cytoscape.view.model.Range;

/**
 * Visual Property for CellFormat values.
 * 
 * @CyAPI.Final.Class
 * @CyAPI.InModule presentation-api
 * @since 3.9
 */
public final class CellFormatVisualProperty extends AbstractVisualProperty<CellFormat> {

	protected static final Range<CellFormat> FORMAT_RANGE = new DiscreteRange<>(CellFormat.class, new HashSet<>()) {
		@Override
		public boolean inRange(CellFormat value) {
			return true;
		}
	};
		
	
	public CellFormatVisualProperty(CellFormat def, String id, String displayName, Class<? extends CyIdentifiable> modelDataType) {
		super(def, FORMAT_RANGE, id, displayName, modelDataType);
	}

	@Override
	public String toSerializableString(CellFormat value) {
		return value.getFormat();
	}

	@Override
	public CellFormat parseSerializableString(String value) {
		return new CellFormat(value);
	}
}

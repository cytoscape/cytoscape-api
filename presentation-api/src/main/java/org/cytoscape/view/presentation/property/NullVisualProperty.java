package org.cytoscape.view.presentation.property;

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

import java.util.HashSet;
import java.util.Set;

import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.view.model.AbstractVisualProperty;
import org.cytoscape.view.model.DiscreteRange;
import org.cytoscape.view.model.NullDataType;
import org.cytoscape.view.model.Range;

/**
 * Visual Property for root. This will not be used in actual visualization. Just
 * a marker node in the tree.
 *
 * @CyAPI.Final.Class
 * @CyAPI.InModule presentation-api
 */
public final class NullVisualProperty extends AbstractVisualProperty<NullDataType> {

	private static final NullDataType dummyObject = new NullDataTypeImpl();
	private static final Range<NullDataType> NULL_RANGE;

	static {
		final Set<NullDataType> nRange = new HashSet<NullDataType>();
		NULL_RANGE = new DiscreteRange<NullDataType>(
				NullDataType.class, nRange);
	}

	/**
	 * Constructor.
	 * @param id A machine readable string identifying this visual property used for XML serialization. 
	 * @param displayName A human readable string used for displays and user interfaces. 
	 */
	public NullVisualProperty(final String id, final String displayName) {
		super(dummyObject, NULL_RANGE, id, displayName, CyIdentifiable.class);
	}

	
	@Override
	public String toSerializableString(final NullDataType value) {
		return value.toString();
	}

	
	@Override
	public NullDataType parseSerializableString(final String text) {
		return dummyObject;
	}
	
	private final static class NullDataTypeImpl implements NullDataType {
		// Dummy class.  Currently this does nothing.
	}
}

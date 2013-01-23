package org.cytoscape.view.presentation.property;

/*
 * #%L
 * Cytoscape Presentation API (presentation-api)
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

import java.util.HashSet;
import java.util.Set;

import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.view.model.AbstractVisualProperty;
import org.cytoscape.view.model.DiscreteRange;
import org.cytoscape.view.model.Range;
import org.cytoscape.view.model.Visualizable;

/**
 * Visual Property to represent abstract concept such as Node or Edge. If
 * rendering engine have this visual property in the lexicon tree and if it's a
 * leaf, it should render it with default settings.
 * 
 * @CyAPI.Final.Class
 * @CyAPI.InModule presentation-api
 */
public final class DefaultVisualizableVisualProperty extends
		AbstractVisualProperty<Visualizable> {

	private static final Visualizable visualizable = new VisualizableImpl();
	private static final Range<Visualizable> VISUALIZABLE_RANGE;

	static {
		final Set<Visualizable> vRange = new HashSet<Visualizable>();
		VISUALIZABLE_RANGE = new DiscreteRange<Visualizable>(
				Visualizable.class, vRange);
	}

	/**
	 * @param id A machine readable string identifying this visual property used for XML serialization. 
	 * @param displayName A human readable string used for displays and user interfaces. 
	 * @param modelDataType The model data type associated with this visual property, e.g. CyNode, CyEdge, or CyNetwork. 
	 */
	public DefaultVisualizableVisualProperty(final String id,
			final String displayName, final Class<? extends CyIdentifiable> modelDataType) {
		super(visualizable, VISUALIZABLE_RANGE, id, displayName, modelDataType);
	}

	@Override
	public String toSerializableString(final Visualizable value) {
		// return value.toString();
		return "DefaultVisualizableVisualProperty(id="+getIdString()+", name="+getDisplayName()+")";
	}

	@Override
	public Visualizable parseSerializableString(final String text) {
		return visualizable;
	}
	
	private static final class VisualizableImpl implements Visualizable {
		// Dummy class.  Currently this does nothing.
	}

}

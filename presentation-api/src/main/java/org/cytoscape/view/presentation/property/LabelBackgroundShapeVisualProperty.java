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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.view.model.AbstractVisualProperty;
import org.cytoscape.view.model.DiscreteRange;
import org.cytoscape.view.presentation.property.values.AbstractVisualPropertyValue;
import org.cytoscape.view.presentation.property.values.LabelBackgroundShape;

/**
 * 
 * @CyAPI.Final.Class
 * @CyAPI.InModule presentation-api
 */
public final class LabelBackgroundShapeVisualProperty extends AbstractVisualProperty<LabelBackgroundShape> {


	/** No label background. */
	public static final LabelBackgroundShape NONE = new LabelBackgroundShapeImpl("None", "NONE");
	/** Rectangular shape. */
	public static final LabelBackgroundShape RECTANGLE = new LabelBackgroundShapeImpl("Rectangle", "RECTANGLE");
	/** Rectangular shape with rounded corners. */
	public static final LabelBackgroundShape ROUND_RECTANGLE = new LabelBackgroundShapeImpl("Round Rectangle", "ROUND_RECTANGLE");
	

	private static final DiscreteRange<LabelBackgroundShape> NODE_SHAPE_RANGE;

	private static final Map<String, LabelBackgroundShape> DEFAULT_SHAPES;

	static {
		DEFAULT_SHAPES = new HashMap<>();
		DEFAULT_SHAPES.put(ROUND_RECTANGLE.getSerializableString().toUpperCase(), ROUND_RECTANGLE);
		DEFAULT_SHAPES.put(RECTANGLE.getSerializableString().toUpperCase(), RECTANGLE);
		DEFAULT_SHAPES.put(NONE.getSerializableString().toUpperCase(), NONE);
		NODE_SHAPE_RANGE = new DiscreteRange<>(LabelBackgroundShape.class, new HashSet<>(DEFAULT_SHAPES.values()));
	}

	/**
	 * Constructor.
	 * @param defaultValue The default NodeShape value.
	 * @param id A machine readable string identifying this visual property used for XML serialization. 
	 * @param displayName A human readable string used for displays and user interfaces. 
	 * @param modelDataType The model data type associated with this visual property, e.g. CyNode, CyEdge, or CyNetwork. 
	 */
	public LabelBackgroundShapeVisualProperty(LabelBackgroundShape defaultValue, String id, String displayName, Class<? extends CyIdentifiable> modelDataType) {
		super(defaultValue, NODE_SHAPE_RANGE, id, displayName, modelDataType);
	}

	@Override
	public String toSerializableString(LabelBackgroundShape value) {
		return value.getSerializableString();
	}

	@Override
	public LabelBackgroundShape parseSerializableString(final String value) {
		LabelBackgroundShape shape = null;
		
		if (value != null)
			shape = DEFAULT_SHAPES.get(value.toUpperCase());
		
		if (shape == null) {
			// Try to find the shape in the range (it might have been added by a private visual lexicon)
			for (var ns : NODE_SHAPE_RANGE.values()) {
				if (ns.getSerializableString().equalsIgnoreCase(value)) {
					shape = ns;
					break;
				}
			}
		}
		
		return shape;
	}

	/**
	 * Returns true if the specified shape is one of the possible default shape values.
	 * @param shape the node shape to be checked.
	 * @return True if the specified shape is one of the possible default shape values.
	 */
	public static boolean isDefaultShape(final LabelBackgroundShape shape) {
		for(var s: DEFAULT_SHAPES.values()) {
			if(shape.equals(s))
				return true;
		}
		return false;
	}

	private static final class LabelBackgroundShapeImpl extends AbstractVisualPropertyValue implements LabelBackgroundShape {
		public LabelBackgroundShapeImpl(final String displayName, final String serializableString) {
			super(displayName, serializableString);
		}
	}
}

package org.cytoscape.view.presentation.property;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.cytoscape.view.model.AbstractVisualProperty;
import org.cytoscape.view.model.DiscreteRange;
import org.cytoscape.view.presentation.property.values.AbstractVisualPropertyValue;
import org.cytoscape.view.presentation.property.values.LineType;

/**
 * Visual Property for {@link LineType} values. This class provide basic default line types.  
 * Others will be provided by rendering engines.
 * 
 * @CyAPI.Final.Class
 */
public final class LineTypeVisualProperty extends AbstractVisualProperty<LineType> {
	
	/** Solid line */
	public static final LineType SOLID = new LineTypeImpl("Solid", "SOLID");
	/** Long dash followed by short space */
	public static final LineType LONG_DASH = new LineTypeImpl("Dash", "LONG_DASH");
	/** Equal dash and space */
	public static final LineType EQUAL_DASH = new LineTypeImpl("Equal Dash", "EQUAL_DASH");
	/** Dash space dot space dash, etc.*/
	public static final LineType DASH_DOT = new LineTypeImpl( "Dash Dot", "DASH_DOT");
	/** Dot space dot, etc. */ 
	public static final LineType DOT = new LineTypeImpl("Dots", "DOT");

	
	private static DiscreteRange<LineType> LINE_TYPE_RANGE;
	private static final Map<String, LineType> lineTypes;
	
	static {
		lineTypes = new HashMap<String, LineType>();
		
		lineTypes.put(SOLID.getSerializableString().toUpperCase(), SOLID);
		lineTypes.put(LONG_DASH.getSerializableString().toUpperCase(), LONG_DASH);
		lineTypes.put(EQUAL_DASH.getSerializableString().toUpperCase(), EQUAL_DASH);
		lineTypes.put(DASH_DOT.getSerializableString().toUpperCase(), DASH_DOT);
		lineTypes.put(DOT.getSerializableString().toUpperCase(), DOT);
		
		LINE_TYPE_RANGE = new DiscreteRange<LineType>(LineType.class, new HashSet<LineType>(lineTypes.values()));
	}

	/**
	 * Constructor.
	 * @param defaultValue The default LineType value.
	 * @param id A machine readable string identifying this visual property used for XML serialization. 
	 * @param displayName A human readable string used for displays and user interfaces. 
	 * @param modelDataType The model data type associated with this visual property, e.g. CyNode, CyEdge, or CyNetwork. 
	 */
	public LineTypeVisualProperty(LineType defaultValue,
			String id, String displayName, Class<?> modelDataType) {
		super(defaultValue, LINE_TYPE_RANGE, id, displayName, modelDataType);
	}

	@Override
	public String toSerializableString(LineType value) {
		return value.getSerializableString();
	}

	@Override
	public LineType parseSerializableString(String value) {
		LineType lineType = null;
		
		if (value != null) {
			value = value.toUpperCase();
			lineType = lineTypes.get(value);
		}
		
		if (lineType == null) lineType = SOLID;
		
		return lineType;
	}
	
	private static final class LineTypeImpl extends AbstractVisualPropertyValue implements LineType {
		public LineTypeImpl(String displayName, String serializableString) {
			super(displayName, serializableString);
		}
	}
}

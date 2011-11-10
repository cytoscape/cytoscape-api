package org.cytoscape.view.presentation.property;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.cytoscape.view.model.AbstractVisualProperty;
import org.cytoscape.view.model.DiscreteRange;
import org.cytoscape.view.presentation.property.values.LineType;

/**
 * #ASKMIKE needs class comment, public method//attribute comments, declare as final
 * @CyAPI.Final.Class
 */
public class LineTypeVisualProperty extends AbstractVisualProperty<LineType> {
	
	// Default basic line types.  Others will be provided from rendering engines.
	public static final LineType SOLID = new LineTypeImpl("Solid", "SOLID");
	public static final LineType LONG_DASH = new LineTypeImpl("Dash", "LONG_DASH");
	public static final LineType EQUAL_DASH = new LineTypeImpl("Equal Dash", "EQUAL_DASH");
	public static final LineType DASH_DOT = new LineTypeImpl( "Dash Dot", "DASH_DOT");
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

	public LineTypeVisualProperty(LineType defaultValue,
			String id, String displayName, Class<?> targetObjectDataType) {
		super(defaultValue, LINE_TYPE_RANGE, id, displayName, targetObjectDataType);
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

		@Override
		public VisualPropertyValue parseSerializableString(String serializableString) {
			// TODO Auto-generated method stub
			return null;
		}
	}
}

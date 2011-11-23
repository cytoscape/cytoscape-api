package org.cytoscape.view.presentation.property;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.cytoscape.view.model.AbstractVisualProperty;
import org.cytoscape.view.model.DiscreteRange;
import org.cytoscape.view.presentation.property.values.ArrowShape;

/**
 * Visual Property for {@link ArrowShape} values.
 * 
 * @CyAPI.Final.Class
 */
public final class ArrowShapeVisualProperty extends AbstractVisualProperty<ArrowShape> {
	
	// Preset arrow shapes
	public static final ArrowShape NONE = new ArrowShapeImpl("No Arrow", "NONE");
	public static final ArrowShape DIAMOND = new ArrowShapeImpl("Diamond", "DIAMOND");
	public static final ArrowShape DELTA = new ArrowShapeImpl("Delta", "DELTA");
	public static final ArrowShape ARROW = new ArrowShapeImpl("Arrow", "ARROW");
	public static final ArrowShape T = new ArrowShapeImpl("T", "T");
	public static final ArrowShape CIRCLE = new ArrowShapeImpl("Circle", "CIRCLE");
	public static final ArrowShape HALF_TOP = new ArrowShapeImpl("Half Top", "HALF_TOP");
	public static final ArrowShape HALF_BOTTOM = new ArrowShapeImpl("Half Bottom", "HALF_BOTTOM");

	private static final DiscreteRange<ArrowShape> ARROW_SHAPE_RANGE;
	private static final Map<String, ArrowShape> DEFAULT_SHAPES;
	
	static {
		DEFAULT_SHAPES = new HashMap<String, ArrowShape>();
		DEFAULT_SHAPES.put(NONE.getSerializableString().toUpperCase(), NONE);
		DEFAULT_SHAPES.put(DIAMOND.getSerializableString().toUpperCase(), DIAMOND);
		DEFAULT_SHAPES.put(DELTA.getSerializableString().toUpperCase(), DELTA);
		DEFAULT_SHAPES.put(ARROW.getSerializableString().toUpperCase(), ARROW);
		DEFAULT_SHAPES.put(T.getSerializableString().toUpperCase(), T);
		DEFAULT_SHAPES.put(CIRCLE.getSerializableString().toUpperCase(), CIRCLE);
		DEFAULT_SHAPES.put(HALF_TOP.getSerializableString().toUpperCase(), HALF_TOP);
		DEFAULT_SHAPES.put(HALF_BOTTOM.getSerializableString().toUpperCase(), HALF_BOTTOM);
		
		ARROW_SHAPE_RANGE = new DiscreteRange<ArrowShape>(ArrowShape.class, new HashSet<ArrowShape>(DEFAULT_SHAPES.values()));
	}
	

	public ArrowShapeVisualProperty(ArrowShape defaultValue, String id, String displayName,
			Class<?> targetObjectDataType) {
		super(defaultValue, ARROW_SHAPE_RANGE, id, displayName, targetObjectDataType);
	}

	@Override
	public String toSerializableString(ArrowShape value) {
		return value.getSerializableString();
	}

	@Override
	public ArrowShape parseSerializableString(String value) {
		ArrowShape shape = null;
		
		if (value != null)
			shape = DEFAULT_SHAPES.get(value.toUpperCase());
		
		return shape;
	}
	
	public static boolean isDefaultShape(final ArrowShape shape) {
		return DEFAULT_SHAPES.containsValue(shape);
	}

	private static final class ArrowShapeImpl extends AbstractVisualPropertyValue implements ArrowShape {

		public ArrowShapeImpl(String displayName, String serializableString) {
			super(displayName, serializableString);
		}

		@Override
		public VisualPropertyValue parseSerializableString(String serializableString) {
			// TODO: FIXME
			// Should be overridden by child class.
			return null;
		}
	}
}

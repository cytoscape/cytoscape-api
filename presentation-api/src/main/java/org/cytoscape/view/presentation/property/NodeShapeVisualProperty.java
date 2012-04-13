package org.cytoscape.view.presentation.property;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.cytoscape.view.model.AbstractVisualProperty;
import org.cytoscape.view.model.DiscreteRange;
import org.cytoscape.view.presentation.property.values.AbstractVisualPropertyValue;
import org.cytoscape.view.presentation.property.values.NodeShape;

/**
 * Visual Property for {@link NodeShape} values.
 * 
 * @CyAPI.Final.Class
 */
public final class NodeShapeVisualProperty extends AbstractVisualProperty<NodeShape> {

	// Presets
	public static final NodeShape RECTANGLE = new NodeShapeImpl("Rectangle", "RECTANGLE");
	public static final NodeShape ROUND_RECTANGLE = new NodeShapeImpl("Round Rectangle", "ROUND_RECTANGLE");
	public static final NodeShape TRIANGLE = new NodeShapeImpl("Triangle", "TRIANGLE");
	public static final NodeShape PARALLELOGRAM = new NodeShapeImpl("Parallelogram", "PARALLELOGRAM");
	public static final NodeShape DIAMOND = new NodeShapeImpl("Diamond", "DIAMOND");
	public static final NodeShape ELLIPSE = new NodeShapeImpl("Ellipse", "ELLIPSE");
	public static final NodeShape HEXAGON = new NodeShapeImpl("Hexagon", "HEXAGON");
	public static final NodeShape OCTAGON = new NodeShapeImpl("Octagon", "OCTAGON");

	private static final DiscreteRange<NodeShape> NODE_SHAPE_RANGE;

	private static final Map<String, NodeShape> DEFAULT_SHAPES;

	static {
		DEFAULT_SHAPES = new HashMap<String, NodeShape>();
		DEFAULT_SHAPES.put(RECTANGLE.getSerializableString().toUpperCase(), RECTANGLE);
		DEFAULT_SHAPES.put(ROUND_RECTANGLE.getSerializableString().toUpperCase(), ROUND_RECTANGLE);
		DEFAULT_SHAPES.put(TRIANGLE.getSerializableString().toUpperCase(), TRIANGLE);
		DEFAULT_SHAPES.put(PARALLELOGRAM.getSerializableString().toUpperCase(), PARALLELOGRAM);
		DEFAULT_SHAPES.put(DIAMOND.getSerializableString().toUpperCase(), DIAMOND);
		DEFAULT_SHAPES.put(ELLIPSE.getSerializableString().toUpperCase(), ELLIPSE);
		DEFAULT_SHAPES.put(HEXAGON.getSerializableString().toUpperCase(), HEXAGON);
		DEFAULT_SHAPES.put(OCTAGON.getSerializableString().toUpperCase(), OCTAGON);
		// Keys of older versions of Cytoscape:
		DEFAULT_SHAPES.put("RECT", RECTANGLE);
		DEFAULT_SHAPES.put("ROUNDRECT", ROUND_RECTANGLE);
		DEFAULT_SHAPES.put("ROUND_RECT", ROUND_RECTANGLE);
		// Regular XGMML keys:
		DEFAULT_SHAPES.put("BOX", RECTANGLE);
		DEFAULT_SHAPES.put("CIRCLE", ELLIPSE);
		DEFAULT_SHAPES.put("VER_ELLIPSIS", ELLIPSE);
		DEFAULT_SHAPES.put("HOR_ELLIPSIS", ELLIPSE);
		DEFAULT_SHAPES.put("RHOMBUS", PARALLELOGRAM);

		NODE_SHAPE_RANGE = new DiscreteRange<NodeShape>(NodeShape.class, new HashSet<NodeShape>(DEFAULT_SHAPES.values()));
	}

	public NodeShapeVisualProperty(NodeShape defaultValue, String id, String displayName, Class<?> targetObjectDataType) {
		super(defaultValue, NODE_SHAPE_RANGE, id, displayName, targetObjectDataType);
	}

	@Override
	public String toSerializableString(NodeShape value) {
		return value.getSerializableString();
	}

	@Override
	public NodeShape parseSerializableString(final String value) {
		NodeShape shape = null;
		
		if (value != null)
			shape = DEFAULT_SHAPES.get(value.toUpperCase());
		
		return shape;
	}

	public static boolean isDefaultShape(final NodeShape shape) {
		return DEFAULT_SHAPES.containsValue(shape);
	}

	private static final class NodeShapeImpl extends AbstractVisualPropertyValue implements NodeShape {
		public NodeShapeImpl(final String displayName, final String serializableString) {
			super(displayName, serializableString);
		}
	}
}

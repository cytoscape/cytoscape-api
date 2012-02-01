package org.cytoscape.view.presentation.property;

import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.ContinuousRange;
import org.cytoscape.view.model.NullDataType;
import org.cytoscape.view.model.VisualProperty;
import org.cytoscape.view.presentation.property.values.ArrowShape;
import org.cytoscape.view.presentation.property.values.LineType;
import org.cytoscape.view.presentation.property.values.NodeShape;

/**
 * Set of Visual Properties for more advanced rendering engines.
 * Rendering engine developers should extend this class to add their own set of Visual Properties.
 * 
 * @CyAPI.Final.Class
 */
public class RichVisualLexicon extends MinimalVisualLexicon {

	private static final double DEF_BORDER_WIDTH = 2.0d;
	private static final int DEF_FONT_SIZE = 12;

	// 3D-related props
	public static final VisualProperty<Double> NODE_Z_LOCATION = new DoubleVisualProperty(0.0, ARBITRARY_DOUBLE_RANGE,
			"NODE_Z_LOCATION", "Node Z Location", true, CyNode.class);

	public static final VisualProperty<Double> NODE_DEPTH = new DoubleVisualProperty(0.0,
			NONE_ZERO_POSITIVE_DOUBLE_RANGE, "NODE_DEPTH", "Node Depth", CyNode.class);

	public static final VisualProperty<Double> NETWORK_CENTER_Z_LOCATION = new DoubleVisualProperty(0.0,
			ARBITRARY_DOUBLE_RANGE, "NETWORK_CENTER_Z_LOCATION", "Network Center Z Location", CyNetwork.class);

	public static final VisualProperty<Double> NETWORK_DEPTH = new DoubleVisualProperty(0.0,
			NONE_ZERO_POSITIVE_DOUBLE_RANGE, "NETWORK_DEPTH", "Network Depth", CyNetwork.class);

	public static final VisualProperty<NodeShape> NODE_SHAPE = new NodeShapeVisualProperty(
			NodeShapeVisualProperty.ELLIPSE, "NODE_SHAPE", "Node Shape", CyNode.class);

	// Line Types
	public static final VisualProperty<LineType> NODE_BORDER_LINE_TYPE = new LineTypeVisualProperty(
			LineTypeVisualProperty.SOLID, "NODE_BORDER_STROKE", "Node Border Line Type", CyNode.class);
	public static final VisualProperty<LineType> EDGE_LINE_TYPE = new LineTypeVisualProperty(
			LineTypeVisualProperty.SOLID, "EDGE_LINE_TYPE", "Edge Line Type", CyEdge.class);

	// Moved from DING rendering engine.
	public static final VisualProperty<Paint> NODE_SELECTED_PAINT = new PaintVisualProperty(Color.YELLOW,
			MinimalVisualLexicon.PAINT_RANGE, "NODE_SELECTED_PAINT", "Node Selected Paint", CyNode.class);
	public static final VisualProperty<Paint> NODE_BORDER_PAINT = new PaintVisualProperty(Color.BLACK,
			MinimalVisualLexicon.PAINT_RANGE, "NODE_BORDER_PAINT", "Node Border Paint", CyNode.class);

	public static final VisualProperty<Double> NODE_BORDER_WIDTH = new DoubleVisualProperty(DEF_BORDER_WIDTH,
			new ContinuousRange<Double>(Double.class, 0d, Double.POSITIVE_INFINITY, true, true), "NODE_BORDER_WIDTH",
			"Node Border Width", CyNode.class);

	public static final VisualProperty<String> NODE_TOOLTIP = new StringVisualProperty("",
			MinimalVisualLexicon.ARBITRARY_STRING_RANGE, "NODE_TOOLTIP", "Node Tooltip", CyNode.class);

	public static final VisualProperty<Font> NODE_LABEL_FONT_FACE = new FontVisualProperty(new Font("SansSerif",
			Font.PLAIN, DEF_FONT_SIZE), "NODE_LABEL_FONT_FACE", "Node Label Font Face", CyNode.class);
	public static final VisualProperty<Integer> NODE_LABEL_FONT_SIZE = new IntegerVisualProperty(DEF_FONT_SIZE,
			new ContinuousRange<Integer>(Integer.class, 1, Integer.MAX_VALUE, true, true), "NODE_LABEL_FONT_SIZE",
			"Node Label Font Size", CyNode.class);

	public static final VisualProperty<Integer> NODE_TRANSPARENCY = new IntegerVisualProperty(200,
			new ContinuousRange<Integer>(Integer.class, 0, 255, true, true), "NODE_TRANSPARENCY", "Node Transparency",
			CyNode.class);
	public static final VisualProperty<Integer> NODE_BORDER_TRANSPARENCY = new IntegerVisualProperty(200,
			new ContinuousRange<Integer>(Integer.class, 0, 255, true, true), "NODE_BORDER_TRANSPARENCY", "Node Border Transparency",
			CyNode.class);

	public static final VisualProperty<Integer> NODE_LABEL_TRANSPARENCY = new IntegerVisualProperty(200,
			new ContinuousRange<Integer>(Integer.class, 0, 255, true, true), "NODE_LABEL_TRANSPARENCY",
			"Node Label Transparency", CyNode.class);

	public static final VisualProperty<String> EDGE_TOOLTIP = new StringVisualProperty("",
			MinimalVisualLexicon.ARBITRARY_STRING_RANGE, "EDGE_TOOLTIP", "Edge Tooltip", CyEdge.class);

	public static final VisualProperty<Font> EDGE_LABEL_FONT_FACE = new FontVisualProperty(new Font("SansSerif",
			Font.PLAIN, 10), "EDGE_LABEL_FONT_FACE", "Edge Label Font Face", CyEdge.class);
	public static final VisualProperty<Integer> EDGE_LABEL_FONT_SIZE = new IntegerVisualProperty(10,
			new ContinuousRange<Integer>(Integer.class, 1, Integer.MAX_VALUE, true, true), "EDGE_LABEL_FONT_SIZE",
			"Edge Label Font Size", CyEdge.class);

	public static final VisualProperty<Paint> EDGE_SELECTED_PAINT = new PaintVisualProperty(Color.RED,
			MinimalVisualLexicon.PAINT_RANGE, "EDGE_SELECTED_PAINT", "Edge Color (Selected)", CyEdge.class);
	public static final VisualProperty<Paint> EDGE_UNSELECTED_PAINT = new PaintVisualProperty(Color.DARK_GRAY,
			MinimalVisualLexicon.PAINT_RANGE, "EDGE_UNSELECTED_PAINT", "Edge Color (Unselected)", CyEdge.class);
	public static final VisualProperty<Paint> EDGE_STROKE_SELECTED_PAINT = new PaintVisualProperty(Color.RED,
			MinimalVisualLexicon.PAINT_RANGE, "EDGE_STROKE_SELECTED_PAINT", "Edge Stroke Color (Selected)",
			CyEdge.class);
	public static final VisualProperty<Paint> EDGE_STROKE_UNSELECTED_PAINT = new PaintVisualProperty(Color.DARK_GRAY,
			MinimalVisualLexicon.PAINT_RANGE, "EDGE_STROKE_UNSELECTED_PAINT", "Edge Stroke Color (Unselected)",
			CyEdge.class);

	public static final VisualProperty<Integer> EDGE_TRANSPARENCY = new IntegerVisualProperty(200,
			new ContinuousRange<Integer>(Integer.class, 0, 255, true, true), "EDGE_TRANSPARENCY", "Edge Transparency",
			CyEdge.class);

	public static final VisualProperty<Integer> EDGE_LABEL_TRANSPARENCY = new IntegerVisualProperty(200,
			new ContinuousRange<Integer>(Integer.class, 0, 255, true, true), "EDGE_LABEL_TRANSPARENCY",
			"Edge Label Transparency", CyEdge.class);

	public static final VisualProperty<ArrowShape> EDGE_SOURCE_ARROW_SHAPE = new ArrowShapeVisualProperty(
			ArrowShapeVisualProperty.NONE, "EDGE_SOURCE_ARROW_SHAPE", "Edge Source Arrow Shape", CyEdge.class);
	public static final VisualProperty<ArrowShape> EDGE_TARGET_ARROW_SHAPE = new ArrowShapeVisualProperty(
			ArrowShapeVisualProperty.NONE, "EDGE_TARGET_ARROW_SHAPE", "Edge Target Arrow Shape", CyEdge.class);

	/**
	 * Construct a {@linkplain org.cytoscape.view.model.VisualLexicon} for 3D
	 * rendering engine.
	 * 
	 * @param root
	 *            Root node in the lexicon tree.
	 * 
	 */
	public RichVisualLexicon(final VisualProperty<NullDataType> root) {
		super(root);

		addVisualProperty(NODE_Z_LOCATION, NODE);
		addVisualProperty(NODE_DEPTH, NODE_SIZE);

		addVisualProperty(NETWORK_CENTER_Z_LOCATION, NETWORK);
		addVisualProperty(NETWORK_DEPTH, NETWORK_SIZE);

		addVisualProperty(NODE_SHAPE, NODE);

		addVisualProperty(NODE_SELECTED_PAINT, NODE_PAINT);
		addVisualProperty(NODE_BORDER_WIDTH, NODE);
		addVisualProperty(NODE_BORDER_LINE_TYPE, NODE);
		addVisualProperty(NODE_TRANSPARENCY, NODE);
		addVisualProperty(NODE_BORDER_TRANSPARENCY, NODE);

		addVisualProperty(NODE_BORDER_PAINT, NODE_PAINT);
		addVisualProperty(NODE_TOOLTIP, NODE);
		addVisualProperty(NODE_LABEL_FONT_SIZE, NODE_SIZE);

		addVisualProperty(NODE_LABEL_FONT_FACE, NODE);
		addVisualProperty(NODE_LABEL_TRANSPARENCY, NODE);

		addVisualProperty(EDGE_LINE_TYPE, EDGE);

		addVisualProperty(EDGE_TOOLTIP, EDGE);

		addVisualProperty(EDGE_LABEL_FONT_FACE, EDGE);
		addVisualProperty(EDGE_LABEL_FONT_SIZE, EDGE);
		addVisualProperty(EDGE_LABEL_TRANSPARENCY, EDGE);

		addVisualProperty(EDGE_SELECTED_PAINT, EDGE_PAINT);
		addVisualProperty(EDGE_UNSELECTED_PAINT, EDGE_PAINT);
		addVisualProperty(EDGE_STROKE_SELECTED_PAINT, EDGE_SELECTED_PAINT);
		addVisualProperty(EDGE_STROKE_UNSELECTED_PAINT, EDGE_UNSELECTED_PAINT);

		addVisualProperty(EDGE_TRANSPARENCY, EDGE);

		addVisualProperty(EDGE_SOURCE_ARROW_SHAPE, EDGE);
		addVisualProperty(EDGE_TARGET_ARROW_SHAPE, EDGE);

		createLookupMap();
	}

	private void createLookupMap() {
		// XGMML:
		addIdentifierMapping(CyNode.class, "type", NODE_SHAPE);
		addIdentifierMapping(CyNode.class, "outline", NODE_BORDER_PAINT);
		addIdentifierMapping(CyNode.class, "width", NODE_BORDER_WIDTH);
		addIdentifierMapping(CyNode.class, "borderLineType", NODE_BORDER_LINE_TYPE);
		addIdentifierMapping(CyNode.class, "nodeLabelFont", NODE_LABEL_FONT_FACE);
		addIdentifierMapping(CyNode.class, "nodeTransparency", NODE_TRANSPARENCY);
		addIdentifierMapping(CyNode.class, "nodeLabelTransparency", NODE_LABEL_TRANSPARENCY);
		addIdentifierMapping(CyNode.class, "nodeBorderTransparency", NODE_BORDER_TRANSPARENCY);

		addIdentifierMapping(CyEdge.class, "fill", EDGE_STROKE_UNSELECTED_PAINT);
		addIdentifierMapping(CyEdge.class, "edgeLineType", EDGE_LINE_TYPE);
		addIdentifierMapping(CyEdge.class, "edgeLabelFont", EDGE_LABEL_FONT_FACE);

		// 2.x VizMap Properties:
		addIdentifierMapping(CyNode.class, "nodeOpacity", NODE_TRANSPARENCY);
		addIdentifierMapping(CyNode.class, "nodeBorderColor", NODE_BORDER_PAINT);
		addIdentifierMapping(CyNode.class, "nodeLineWidth", NODE_BORDER_WIDTH);
		addIdentifierMapping(CyNode.class, "nodeLineStyle", NODE_BORDER_LINE_TYPE);
		addIdentifierMapping(CyNode.class, "nodeShape", NODE_SHAPE);
		addIdentifierMapping(CyNode.class, "nodeFont", NODE_LABEL_FONT_FACE);
		addIdentifierMapping(CyNode.class, "nodeFontSize", NODE_LABEL_FONT_SIZE);
		addIdentifierMapping(CyNode.class, "nodeToolTip", NODE_TOOLTIP);

		addIdentifierMapping(CyEdge.class, "edgeColor", EDGE_STROKE_UNSELECTED_PAINT);
		addIdentifierMapping(CyEdge.class, "edgeLineStyle", EDGE_LINE_TYPE);
		addIdentifierMapping(CyEdge.class, "edgeToolTip", EDGE_TOOLTIP);
		addIdentifierMapping(CyEdge.class, "edgeFont", EDGE_LABEL_FONT_FACE);
		addIdentifierMapping(CyEdge.class, "edgeFontSize", EDGE_LABEL_FONT_SIZE);
		addIdentifierMapping(CyEdge.class, "edgeLabelTransparency", EDGE_LABEL_TRANSPARENCY);

		addIdentifierMapping(CyEdge.class, "edgeTransparency", EDGE_TRANSPARENCY);

		addIdentifierMapping(CyEdge.class, "sourceArrow", EDGE_SOURCE_ARROW_SHAPE);
		addIdentifierMapping(CyEdge.class, "targetArrow", EDGE_TARGET_ARROW_SHAPE);
		addIdentifierMapping(CyEdge.class, "edgeSourceArrowShape", EDGE_SOURCE_ARROW_SHAPE);
		addIdentifierMapping(CyEdge.class, "edgeTargetArrowShape", EDGE_TARGET_ARROW_SHAPE);

		// TODO add more!
	}
}

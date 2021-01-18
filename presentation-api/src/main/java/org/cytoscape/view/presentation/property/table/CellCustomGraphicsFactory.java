package org.cytoscape.view.presentation.property.table;

import java.util.Map;

import javax.swing.Icon;
import javax.swing.JComponent;

/**
 * <p>
 * Factory to create {@link CellCustomGraphics} objects. 
 * CellCustomGraphicsFactory objects are registered as OSGi services and 
 * will be used by Renderers to create the actual custom graphics implementations. 
 * </p>
 * <p>
 * The pattern is to register CellCustomGraphicsFactory implementations as OSGi services in your CyActivator class.
 * </p>
 * 
 * <pre>
 * CellCustomGraphicsFactory myCgFactory = new MyCustomGraphicsFactory();
 * registerService(bundleContext, myCgFactory, CellCustomGraphicsFactory.class, new Properties());
 * </pre>
 * 
 * <h2>Sparklines</h2>
 * 
 * <p>
 * Cytoscape provides some predefined custom graphics factories for creating sparklines.
 * These factories can be retrieved as OSGi services by using their IDs.
 * </p>
 * 
 * TODO:
 * <table border="1">
 * <tr><td>Bar Sparkline</td><td><code>org.cytoscape.BarCellSparkline</code></td>
 * <tr><td>Box Sparkline</td><td><code>org.cytoscape.BoxCellSparkline</code></td>
 * <tr><td>Heat Map Sparkline</td><td><code>org.cytoscape.HeatMapCellSparkline</code></td>
 * <tr><td>Line Sparkline</td><td><code>org.cytoscape.LineCellSparkline</code></td>
 * <tr><td>Pie Sparkline</td><td><code>org.cytoscape.PieCellSparkline</code></td>
 * <tr><td>Ring Sparkline</td><td><code>org.cytoscape.RingCellSparkline</code></td>
 * <tr><td>Linear Gradient</td><td><code>org.cytoscape.LinearCellGradient</code></td>
 * <tr><td>Radial Gradient</td><td><code>org.cytoscape.RadialCellGradient</code></td>
 * </table>
 * 
 * <p>
 * To retrieve a reference to a predefined sparkline factory you must use an OSGi service listener.
 * For example:
 * </p>
 * 
 * <pre>
 * public class CustomChartListener {
 *	private static final String FACTORY_ID = "org.cytoscape.PieChart";
 *	private CellCustomGraphicsFactory&lt;?&gt; factory;
 *	
 *	public void addCustomGraphicsFactory(CellCustomGraphicsFactory&lt;?&gt; factory, Map&lt;Object,Object&gt; serviceProps) {
 *		if(FACTORY_ID.equals(factory.getId())) {
 *			this.factory = factory;
 *		}
 *	}
 *	
 *	public void removeCustomGraphicsFactory(CellCustomGraphicsFactory&lt;?&gt; factory, Map&lt;Object,Object&gt; serviceProps) {
 *		this.factory = null;
 *	}
 *	
 *	public CellCustomGraphicsFactory&lt;?&gt; getFactory() {
 *		return factory;
 *	}
 * }
 * </pre>
 * 
 * <p>Register the listener in your CyActivator class.</p>
 * 
 * <pre>
 *   CustomChartListener customChartListener = new CustomChartListener();
 *   registerServiceListener(context, customChartListener, "addCustomGraphicsFactory", "removeCustomGraphicsFactory", CellCustomGraphicsFactory.class);
 * </pre>
 * 
 * <p>
 * Use the factory to create an instance of CellCustomGraphics for your sparklines.
 * The data and appearance of the sparklines are controlled by a Map of properties
 * that are passed to the getInstance() method.
 * </p>
 * 
 * <pre>
 * CellCustomGraphicsFactory&lt;?&gt; customGraphicsFactory = customChartListener.getFactory();
 * CyColumnIdentifierFactory columnIdFactory; // Get OSGi service
 * 
 * CyColumnIdentifier columnId = columnIdFactory.createColumnIdentifier(chartColumn);
 * Map&lt;String,Object&gt; chartProps = new HashMap&lt;String,Object&gt;();
 * chartProps.put("cy_dataColumns", Arrays.asList(columnId)); 
 * chartProps.put("cy_colorScheme", "CONTRASTING");
 *		
 * CellCustomGraphics&lt;?&gt; customGraphics = customGraphicsFactory.getInstance(chartProps);
 *
 * // Set the custom graphics on the visual style
 * VisualStyle visualStyle = visualMappingManager.getCurrentVisualStyle();
 * CyApplicationManager appManager; // Get OSGi service
 * RenderingEngine<?> engine = appManager.getCurrentRenderingEngine();
 * VisualLexicon lexicon = engine.getVisualLexicon();
 * VisualProperty&lt;CyCustomGraphics&gt; visualProperty = lexicon.lookup(CyNode.class, "NODE_CUSTOMGRAPHICS_1");
 * 
 * if (visualProperty != null)
 *     visualStyle.setDefaultValue(visualProperty, customGraphics);
 * </pre>
 * 
 * <h3>Sparkline Properties</h3>
 * 
 * All built-in properties start with the "cy_" prefix. If you are writing an App that provides
 * additional properties please specify your own prefix in order to prevent name collisions.
 * 
 * <h4>All Charts</h4>
 * <table border="1">
 * <tr><th>Property Name</th><th>Type</th><th>Description</th><tr>
 * <tr><td>{@code cy_dataColumns}</td><td>{@code List<CyColumnIdentifier>}</td><td>Names of data columns from the default node table. Columns of type List become separate groups (or data series) in the chart (for example a ring chart will have a separate ring for each group). The column type must be numerical.</td></tr>
 * <tr><td>{@code cy_values}</td><td>{@code List<Double>}</td><td>Specific values to use for each segment of the chart. If {@code cy_dataColumns} is specified, this property does not need to be set.</td></tr> 
 * <tr><td>{@code cy_colors}</td><td>{@code List<java.awt.Color>}</td><td>List of specific colors to use with each data column (if the column contains single numbers) or value. The color list should have one entry for every corresponding entry in the {@code cy_dataColumns} property list, if the list contains only columns of simple numerical types (no List types). If {@code cy_dataColumns} contains List-typed columns, it must contain as many colors as elements in the list values.</td></tr>
 * <tr><td>{@code cy_colorScheme}</td><td>{@code String}</td><td>Name of a predefined color scheme. Use this property instead of {@code cy_colors} to have the colors chosen automatically. Values: CONTRASTING, MODULATED, RAINBOW, RANDOM</td></tr> 
 * <tr><td>{@code cy_itemLabels}</td><td>{@code List<String>}</td><td>Labels to use for each segment of the chart (for example each slice of a pie chart.) The label list should have one entry for every corresponding entry in the {@code cy_dataColumns} property list.</td></tr>
 * <tr><td>{@code cy_itemLabelsColumn}</td><td>{@code CyColumnIdentifier}</td><td>Name of a data column to use for value labels. The column should be of type List, each element in the list will be used as a label.</td></tr>
 * <tr><td>{@code cy_showItemLabels}</td><td>{@code Boolean}</td><td>Set to true to show value labels</td></tr>
 * <tr><td>{@code cy_borderWidth}</td><td>{@code Float}</td><td>Border width</td></tr>
 * <tr><td>{@code cy_borderColor}</td><td>{@code java.awt.Color}</td><td>Border color</td></tr>
 * </table>
 * 
 * <h4>Bar/Box/Line/Heat Charts</h4>
 * <table border="1">
 * <tr><th>Property Name</th><th>Type</th><th>Description</th><tr>
 * <tr><td>{@code cy_orientation}</td><td>{@code String}</td><td>Values: HORIZONTAL, VERTICAL</td></tr>
 * <tr><td>{@code cy_domainLabelsColumn}</td><td>{@code CyColumnIdentifier}</td><td>Name of a data column to use for domain labels. The column should be of type List.</td></tr>
 * <tr><td>{@code cy_rangeLabelsColumn}</td><td>{@code CyColumnIdentifier}</td><td>Name of a data column to use for range labels. The column should be of type List.</td></tr>
 * <tr><td>{@code cy_domainLabelPosition}</td><td>{@code String}</td><td>Values: STANDARD, DOWN_45, DOWN_90, UP_45, UP_90</td></tr>
 * <tr><td>{@code cy_globalRange}</td><td>{@code Boolean}</td><td>If true, all charts' range (min and max bounds) will be automatically set to the network-wide range.</td></tr>
 * <tr><td>{@code cy_range}</td><td>{@code List<Double>}</td><td>Allows the global range to be set manually. Must be a list with exactly two elements. Specifies the lower (first element) and upper bound for the range axis. The property {@code cy_range} must be set to true.</td></tr>
 * <tr><td>{@code cy_showDomainAxis}</td><td>{@code Boolean}</td><td>Set to true to show the domain axis.</td></tr>
 * <tr><td>{@code cy_showRangeAxis}</td><td>{@code Boolean}</td><td>Set to true to show the range axis.</td></tr>
 * <tr><td>{@code cy_axisWidth}</td><td>{@code Float}</td><td>Axis stroke width.</td></tr>
 * <tr><td>{@code cy_axisColor}</td><td>{@code java.awt.Color}</td><td>Axis line color.</td></tr>
 * </table>
 * 
 * <h4>Bar Charts</h4>
 * <table border="1">
 * <tr><th>Property Name</th><th>Type</th><th>Description</th><tr>
 * <tr><td>{@code cy_type}</td><td>{@code String}</td><td>Values: GROUPED, STACKED, HEAT_STRIPS, UP_DOWN</td></tr>
 * <tr><td>{@code cy_separation}</td><td>{@code Double}</td><td>Separation between bars. Value must be between 0.0 and 0.5</td></tr>
 * </table>
 * 
 * <h4>Line Charts</h4>
 * <table border="1">
 * <tr><th>Property Name</th><th>Type</th><th>Description</th><tr>
 * <tr><td>{@code cy_lineWidth}</td><td>{@code Float}</td><td>Line width</td></tr>
 * </table>
 * 
 * <h4>Pie Charts</h4>
 * <table border="1">
 * <tr><th>Property Name</th><th>Type</th><th>Description</th><tr>
 * <tr><td>{@code cy_startAngle}</td><td>{@code Double}</td><td>Start angle for the first pie section.</td></tr>
 * </table>
 * 
 * <h4>Ring Charts</h4>
 * <table border="1">
 * <tr><th>Property Name</th><th>Type</th><th>Description</th><tr>
 * <tr><td>{@code cy_startAngle}</td><td>{@code Double}</td><td>Start angle for the first section.</td></tr>
 * <tr><td>{@code cy_holeSize}</td><td>{@code Double}</td><td>Width of the hole in the center of the ring.</td></tr>
 * </table>
 * 
 * <h4>Gradients</h4>
 * <table border="1">
 * <tr><th>Property Name</th><th>Type</th><th>Description</th><tr>
 * <tr><td>{@code cy_gradientFractions}</td><td>{@code List<Float>}</td><td>Numbers ranging from 0.0 to 1.0 specifying the distribution of colors along the gradient. See javadocs for {@code java.awt.MultipleGradientPaint} for more detail.</td></tr>
 * <tr><td>{@code cy_gradientColors}</td><td>{@code List<java.awt.Color>}</td><td>List of colors corresponding to each fraction value.</td></tr>
 * </table>
 * 
 * <h4>Linear Gradient</h4>
 * <table border="1">
 * <tr><th>Property Name</th><th>Type</th><th>Description</th><tr>
 * <tr><td>{@code cy_angle}</td><td>{@code Double}</td><td>Slope (rotation) of the gradient, in degrees.</td></tr>
 * </table>
 * 
 * <h4>Radial Gradient</h4>
 * <table border="1">
 * <tr><th>Property Name</th><th>Type</th><th>Description</th><tr>
 * <tr><td>{@code cy_center}</td><td>{@code java.awt.geom.Point2D}</td><td>Center of the gradient. Each coordinate must be between 0.0 and 1.0.</td></tr>
 * </table>
 * 
 * <br><br>
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule presentation-api
 * 
 */
public interface CellCustomGraphicsFactory {
	
	/**
	 * Optional property key that tells Cytoscape under which group it 
	 * should add the editor created by this factory (see {@link #createEditor(CellCustomGraphics)}).
	 */
	static final String GROUP = "group";
	
	/**
 	 * Return the prefix to identify this Custom Graphics factory.
 	 * This is used by the passthrough mapping logic to figure out if a
 	 * given String value should be mapped to this factory.
 	 *
 	 * @return the prefix for this CellCustomGraphicsFactory
 	 */
	String getId();
	
	/**
	 * Return the {@link CellCustomGraphics} name which is will be displayed to the user.
	 * 
	 * @return display name as String.
	 */
	String getDisplayName();
	
	/**
	 * @param width
	 * @param height
	 * @return an optional icon that represents a custom graphics type.
	 */
	Icon getIcon(int width, int height);
	
	/**
 	 * Get a new instance of a {@link CellCustomGraphics}.  The string argument may
 	 * be used by some implementations to create the initial {@link CellCustomGraphics} objects.
 	 * This is the method that will be used to take a String passthrough mapping and create the
 	 * correct {@link CellCustomGraphics} instance.  Note that the prefix defined
 	 * above will get removed from the string before this method is called.
 	 *
 	 * @param input a possible input string that may be used to create the
 	 *              instance.  Not all implementations will use this.
 	 * @return the new instance
 	 */
	CellCustomGraphics getInstance(String input);
	
	/**
	 * Get a new instance of a {@link CellCustomGraphics} that is a clone of the passed argument.
	 * 
	 * @param customGraphics another Custom Graphics
	 * @return the new instance
	 */
	CellCustomGraphics getInstance(CellCustomGraphics customGraphics);
	
	/**
	 * Get a new instance of a {@link CellCustomGraphics} with the passed properties.
	 * The properties object should contain the same keys that are returned by the corresponding
	 * {@link CellCustomGraphics#getProperties()} implementation.
	 * 
	 * @param properties optional properties to initialize the new custom graphics.
	 * @return the new instance
	 */
	CellCustomGraphics getInstance(Map<String, Object> properties);
	
	/**
 	 * @return the class that this factory creates.  This is used by the deserialization
 	 * mechanism to find the factory method that can deserialize a given string.
 	 */
	Class<? extends CellCustomGraphics> getSupportedClass();
	
	/**
	 * Creates a UI component that configures the given {@code CellCustomGraphics}.
	 * 
	 * @param customGraphics the {@link CellCustomGraphics} to be configured.
	 * @return a UI panel that configures the given {@code CellCustomGraphics}
	 *         or null if the factory does not want to provide a visual editor.
	 */
	JComponent createEditor(CellCustomGraphics customGraphics);
	
}

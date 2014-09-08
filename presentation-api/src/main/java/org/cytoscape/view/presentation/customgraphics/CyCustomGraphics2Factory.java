package org.cytoscape.view.presentation.customgraphics;

import java.util.Map;

import javax.swing.Icon;
import javax.swing.JComponent;

/**
 * This interface provides the factory to create {@link CyCustomGraphics2} objects. 
 * CyCustomGraphics2Factory objects should be registered as services in
 * OSGi and will be used by Renderers to create the actual custom graphics
 * implementations.  Note that the type of a CyCustomGraphics2Factory is
 * the type of the underlying {@link CustomGraphicLayer} not the type
 * of the resulting {@link CyCustomGraphics2} object this creates. In general,
 * the pattern is to add to your CyActivator class:
 *
 * <pre>	
  		CyCustomGraphics2Factory myCustomGraphics2Factory = new MyCustomGraphics2Factory();
  		registerService(bundleContext, myCustomGraphics2Factory, CyCustomGraphics2Factory.class, new Properties());
  </pre>
 *
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule presentation-api
 */
public interface CyCustomGraphics2Factory<T extends CustomGraphicLayer> {
	
	/**
	 * Optional property key that tells Cytoscape under which group it 
	 * should add the editor created by this factory (see {@link #createEditor(CyCustomGraphics2)}).
	 */
	static final String GROUP = "group";
	
	/**
 	 * Return the prefix to identify this Custom Graphics factory.
 	 * This is used by the passthrough mapping logic to figure out if a
 	 * given String value should be mapped to this factory.
 	 *
 	 * @return the prefix for this CyCustomGraphics2Factory
 	 */
	String getId();
	
	/**
	 * Return the {@link CyCustomGraphics2} name which is will be displayed to the user.
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
 	 * Get a new instance of a {@link CyCustomGraphics2}.  The string argument may
 	 * be used by some implementations to create the initial {@link CyCustomGraphics2} objects.
 	 * This is the method that will be used to take a String passthrough mapping and create the
 	 * correct {@link CyCustomGraphics2} instance.  Note that the prefix defined
 	 * above will get removed from the string before this method is called.
 	 *
 	 * @param input a possible input string that may be used to create the
 	 *              instance.  Not all implementations will use this.
 	 * @return the new instance
 	 */
	CyCustomGraphics2<T> getInstance(String input);
	
	/**
	 * Get a new instance of a {@link CyCustomGraphics2} that is a clone of the passed argument.
	 * 
	 * @param customGraphics another Custom Graphics
	 * @return the new instance
	 */
	CyCustomGraphics2<T> getInstance(CyCustomGraphics2<T> customGraphics);
	
	/**
	 * Get a new instance of a {@link CyCustomGraphics2} with the passed properties.
	 * The properties object should contain the same keys that are returned by the corresponding
	 * {@link CyCustomGraphics2#getProperties()} implementation.
	 * 
	 * @param properties optional properties to initialize the new custom graphics.
	 * @return the new instance
	 */
	CyCustomGraphics2<T> getInstance(Map<String, Object> properties);
	
	/**
 	 * @return the class that this factory creates.  This is used by the deserialization
 	 * mechanism to find the factory method that can deserialize a given string.
 	 */
	Class<? extends CyCustomGraphics2<T>> getSupportedClass();
	
	/**
	 * Creates a UI component that configures the given {@code CyCustomGraphics2}.
	 * 
	 * @param customGraphics the {@link CyCustomGraphics2} to be configured.
	 * @return a UI panel that configures the given {@code CyCustomGraphics2}
	 *         or null if the factory does not want to provide a visual editor.
	 */
	JComponent createEditor(CyCustomGraphics2<T> customGraphics);
	
}

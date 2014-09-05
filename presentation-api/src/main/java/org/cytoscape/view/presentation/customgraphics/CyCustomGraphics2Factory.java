package org.cytoscape.view.presentation.customgraphics;

import java.util.Map;

import javax.swing.Icon;
import javax.swing.JComponent;

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
	 * 
	 * @param width
	 * @param height
	 * @return
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
	 * 
	 * @param properties optional properties to initialize the new custom graphics.
	 * @return the new instance
	 */
	CyCustomGraphics2<T> getInstance(Map<String, Object> properties);
	
	/**
 	 * Return the class that this factory creates.  This is used by the deserialization
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

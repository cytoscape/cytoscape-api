package org.cytoscape.view.presentation.customgraphics;

import java.awt.Image;
import java.util.List;


/**
 * This interface provides the factory to create CyCustomGraphics objects.
 * Implementations of CyCustomGraphicsFactory are the objects that should
 * be registered as OSGi services and will be utilized by the renderers to
 * create CyCustomGraphics objects.
 *
 */
public interface CyCustomGraphicsFactory<T extends CustomGraphicLayer> {
	/**
 	 * Get a new instance of the CyCustomGraphics.  The string argument may
 	 * be used by some implementations to create the initial graphics objects.
 	 * For example, a bitmap graphic implementation might use the input argument
 	 * as a URI that gives the location of an image file.
 	 *
 	 * @param input a possible input string that may be used to create the
 	 *              instance.  Not all implementations will use this.
 	 * @return the new instance
 	 */
	public CyCustomGraphics<T> getInstance(String input); 

	/**
 	 * Create a new CyCustomGraphics object by parsing the string
 	 * resulting from the toSerializableString() method.
 	 */
	public CyCustomGraphics<T> parseSerializableString(String string);

	/**
 	 * Return the class that this factory creates.  This is used by the deserialization
 	 * mechanism to find the factory method that can deserialize a given string.
 	 */
	public Class<? extends CyCustomGraphics> getSupportedClass();
}

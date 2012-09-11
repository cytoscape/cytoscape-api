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
 	 * Get a new instance of the CyCustomGraphics
 	 */
	public CyCustomGraphics<T> getInstance(); 

	/**
 	 * Create a new CyCustomGraphics object by parsing the string
 	 * resulting from the toSerializableString() method.
 	 */
	public CyCustomGraphics<T> parseSerializableString(String string);
}

package org.cytoscape.view.presentation.customgraphics;

import java.util.Map;

import org.cytoscape.view.presentation.property.values.VisualPropertyValue;

/**
 * Just an extension of {@link CyCustomGraphics} which provides a way for the custom graphics instance
 * to define itself as a set of properties.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule presentation-api
 */
public interface CyCustomGraphics2<T extends CustomGraphicLayer> extends CyCustomGraphics<T>, VisualPropertyValue {

	/**
	 * Provides an optional map of key/value properties which define the custom graphics instance.
	 * @return A map of key/value properties.
	 */
	Map<String, Object> getProperties();
	
}

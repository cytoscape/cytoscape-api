package org.cytoscape.view.vizmap.gui.util;

import java.util.Map;
import java.util.Set;

/**
 * Generates Discrete mapping for a set of attribute values and a Visual
 * Property.
 * 
 * @param <V> the type of the Visual Property.
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule vizmap-gui-api
 */
public interface DiscreteMappingGenerator<V> {

	/**
	 * For a given set of attribute values, create discrete mapping.
	 * 
	 * @param <T>
	 *            the type of the attribute values.
	 * @param attributeSet
	 *            the set of attribute values.
	 * @return a map between an attribute type and a visual property type.
	 */
	<T> Map<T, V> generateMap(final Set<T> attributeSet);

	/**
	 * Returns the type of the visual property.
	 * 
	 * @return the type of the visual property.
	 */
	Class<V> getDataType();
}

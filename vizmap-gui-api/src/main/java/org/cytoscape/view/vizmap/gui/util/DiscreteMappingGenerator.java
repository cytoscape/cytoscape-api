package org.cytoscape.view.vizmap.gui.util;

import java.util.Map;
import java.util.Set;

/** Generates Discrete mappings for sets of attribute values and a visual property type. 
 * @param <V> the type of the visual property. 
 * @CyAPI.Spi.Interface
 */
public interface DiscreteMappingGenerator<V> {
	
	/**
	 * For a given set of attribute values, create discrete mapping.
	 * 
	 * @param <T> the type of the attribute values.
	 * @param attributeSet the set of attribute values.
	 * @return a map between an attribute type and a visual property type.
	 */
	public <T> Map<T, V> generateMap(final Set<T> attributeSet);
	
	/**
	 * Returns the type of the visual property.
	 * @return the type of the visual property.
	 */
	public Class<V> getDataType();
}

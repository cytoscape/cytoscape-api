package org.cytoscape.view.vizmap.gui.util;

import java.util.Map;
import java.util.Set;

public interface DiscreteMappingGenerator<V> {
	
	/**
	 * For a given set of attribute values, create discrete mapping.
	 * 
	 * @param <T>
	 * @param attributeSet
	 * @return
	 */
	public <T> Map<T, V> generateMap(final Set<T> attributeSet);
	
	public Class<V> getDataType();
}

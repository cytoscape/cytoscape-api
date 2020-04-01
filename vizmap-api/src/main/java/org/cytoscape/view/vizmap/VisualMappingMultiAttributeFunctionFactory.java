package org.cytoscape.view.vizmap;

import java.util.List;

import org.cytoscape.view.model.VisualProperty;

public interface VisualMappingMultiAttributeFunctionFactory {

	<K, V> VisualMappingFunction<List<K>, V> createVisualMappingFunction(List<String> attributeNames,
			final Class<K> attrValueType, final VisualProperty<V> vp);

	Class<?> getMappingFunctionType();
	
}

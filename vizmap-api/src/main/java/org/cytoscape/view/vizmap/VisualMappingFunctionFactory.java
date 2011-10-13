package org.cytoscape.view.vizmap;

import org.cytoscape.view.model.VisualProperty;

/**
 * Factory for {@linkplain VisualMappingFunction}s. Implementation of this will
 * be provided as an service.  One mapping type should have one factory.
 * 
 */
public interface VisualMappingFunctionFactory {

	/**
	 * Create a new {@linkplain VisualMappingFunction}.
	 * 
	 * @param <K> Data type of controlling attribute.
	 * @param <V> Data type of {@linkplain VisualProperty}, such as {@linkplain java.awt.Color}, {@linkplain String}, etc.
	 * 
	 * @param attributeName Controlling attribute name.  This is a name of the column in a data table.
	 * @param attrValueType Data type of controlling attribute.
	 * @param vp {@linkplain VisualProperty} used in the new mapping
	 * 
	 * @return new VisualMappingFunction.
	 */
	<K, V> VisualMappingFunction<K, V> createVisualMappingFunction(
			final String attributeName, final Class<K> attrValueType,
			final VisualProperty<V> vp);
	
	/**
	 * Returns the type of this VisualMappingFunctionFactory.
	 * @return the type of this VisualMappingFunctionFactory.
	 */
	Class<?> getMappingFunctionType();
}

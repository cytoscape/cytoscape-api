package org.cytoscape.view.vizmap.mappings;

import org.cytoscape.view.vizmap.VisualMappingFunction;

/**
 *
 * @param <K>
 * @param <V>
 * @CyAPI.Api.Interface
 */
public interface PassthroughMapping<K, V> extends VisualMappingFunction<K,V> {

	public static final String PASSTHROUGH = "Passthrough Mapping";

}
package org.cytoscape.view.vizmap.mappings;

import org.cytoscape.view.vizmap.VisualMappingFunction;

/**
 * An interface describing a passthrough or identity mapping from attribute value
 * to visual property.
 * @param <K> Generic type of the attribute mapped.
 * @param <V> Generic type of the VisualProperty used in this mapping.
 * @CyAPI.Api.Interface
 */
public interface PassthroughMapping<K, V> extends VisualMappingFunction<K,V> {

	/** A label describing the mapping. */
	public static final String PASSTHROUGH = "Passthrough Mapping";

}

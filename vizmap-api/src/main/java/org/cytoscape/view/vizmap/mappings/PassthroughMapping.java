package org.cytoscape.view.vizmap.mappings;

import org.cytoscape.model.CyTableEntry;
import org.cytoscape.view.model.View;
import org.cytoscape.view.vizmap.VisualMappingFunction;

public interface PassthroughMapping<K, V> extends VisualMappingFunction<K,V> {

	public static final String PASSTHROUGH = "Passthrough Mapping";

}
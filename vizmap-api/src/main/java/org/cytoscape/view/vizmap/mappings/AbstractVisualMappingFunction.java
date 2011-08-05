package org.cytoscape.view.vizmap.mappings;

import org.cytoscape.view.model.VisualProperty;
import org.cytoscape.view.vizmap.VisualMappingFunction;

/**
 * All fields are immutable.
 * 
 * @author kono
 *
 * @param <K>
 * @param <V>
 */
public abstract class AbstractVisualMappingFunction<K, V> implements
		VisualMappingFunction<K, V> {

	// Mapping attribute name.  This is immutable.
	protected final String attrName;
	
	// Type of attribute
	protected final Class<K> attrType;

	// Visual Property used in this mapping.
	protected final VisualProperty<V> vp;

	
	public AbstractVisualMappingFunction(final String attrName, final Class<K> attrType,
			final VisualProperty<V> vp) {
		this.attrType = attrType;
		this.attrName = attrName;
		this.vp = vp;
	}

	@Override public String getMappingAttributeName() {
		return attrName;
	}

	
	@Override public Class<K> getMappingAttributeType() {
		return attrType;
	}

	@Override public VisualProperty<V> getVisualProperty() {
		return vp;
	}
}

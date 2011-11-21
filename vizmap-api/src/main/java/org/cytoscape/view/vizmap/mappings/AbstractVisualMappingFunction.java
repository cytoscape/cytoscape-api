package org.cytoscape.view.vizmap.mappings;

import org.cytoscape.model.CyTable;
import org.cytoscape.view.model.VisualProperty;
import org.cytoscape.view.vizmap.VisualMappingFunction;

/**
 * All fields are immutable.
 * 
 * @author kono
 *
 * @param <K> Generic type of the attribute mapped.
 * @param <V> Generic type of the {@link VisualProperty} used in this mapping.
 * @CyAPI.Abstract.Class
 */
public abstract class AbstractVisualMappingFunction<K, V> implements
		VisualMappingFunction<K, V> {

	/** Mapping attribute name.  This is immutable. */
	protected final String attrName;
	
	/** Type of attribute. */
	protected final Class<K> attrType;
	
	protected final CyTable table;

	/** Visual Property used in this mapping. */
	protected final VisualProperty<V> vp;

	
	/**
	 * Constructs this AbstractVisualMappingFunction.
	 * @param attrName Mapping attribute name.
	 * @param attrType Type of attribute.
	 * @param vp Visual Property used in this mapping.
	 */
	public AbstractVisualMappingFunction(final String attrName, final Class<K> attrType, final CyTable table,
			final VisualProperty<V> vp) {
		this.attrType = attrType;
		this.attrName = attrName;
		this.vp = vp;
		this.table = table;
	}

	@Override public String getMappingColumnName() {
		return attrName;
	}

	
	@Override public Class<K> getMappingColumnType() {
		return attrType;
	}

	@Override public VisualProperty<V> getVisualProperty() {
		return vp;
	}
	
	@Override public CyTable getMappingTable() {
		return table;
	}
}

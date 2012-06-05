package org.cytoscape.view.vizmap.mappings;

import org.cytoscape.model.CyTable;
import org.cytoscape.view.model.VisualProperty;
import org.cytoscape.view.vizmap.VisualMappingFunction;

/**
 * A base class for visual mapping functions where all fields are immutable.
 * 
 * @param <K>
 *            Generic type of the attribute mapped.
 * @param <V>
 *            Generic type of the {@link VisualProperty} used in this mapping.
 * @CyAPI.Abstract.Class
 */
public abstract class AbstractVisualMappingFunction<K, V> implements VisualMappingFunction<K, V> {

	/** Mapping attribute name. */
	protected final String columnName;

	/** Type of attribute. */
	protected final Class<K> columnType;

	/** The table used for this mapping. */
	protected final CyTable table;

	/** Visual Property used in this mapping. */
	protected final VisualProperty<V> vp;

	/**
	 * Constructs this AbstractVisualMappingFunction.
	 * 
	 * @param columnName
	 *            Mapping attribute column name.
	 * @param columnType
	 *            Type of attribute column.
	 * @param table
	 *            The table used to find the column.
	 * @param vp
	 *            Visual Property used in this mapping.
	 */
	public AbstractVisualMappingFunction(final String columnName, final Class<K> columnType, final CyTable table,
			final VisualProperty<V> vp) {
		this.columnType = columnType;
		this.columnName = columnName;
		this.vp = vp;
		this.table = table;
	}

	@Override
	public String getMappingColumnName() {
		return columnName;
	}

	@Override
	public Class<K> getMappingColumnType() {
		return columnType;
	}

	@Override
	public VisualProperty<V> getVisualProperty() {
		return vp;
	}

	@Override
	public CyTable getMappingTable() {
		return table;
	}
}

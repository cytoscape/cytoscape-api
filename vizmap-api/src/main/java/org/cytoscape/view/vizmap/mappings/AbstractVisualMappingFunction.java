package org.cytoscape.view.vizmap.mappings;

/*
 * #%L
 * Cytoscape VizMap API (vizmap-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2021 The Cytoscape Consortium
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

import org.cytoscape.event.CyEventHelper;
import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.model.CyRow;
import org.cytoscape.view.model.View;
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
 * @CyAPI.InModule vizmap-api
 */
public abstract class AbstractVisualMappingFunction<K, V> implements VisualMappingFunction<K, V> {

	/** Mapping attribute name. */
	protected final String columnName;

	/** Type of attribute. */
	protected final Class<K> columnType;

	/** Visual Property used in this mapping. */
	protected final VisualProperty<V> vp;
	
	protected final CyEventHelper eventHelper;

	/**
	 * Constructs this AbstractVisualMappingFunction.
	 * 
	 * @param columnName
	 *            Mapping attribute column name.
	 * @param columnType
	 *            Type of attribute column.
	 * @param vp
	 *            Visual Property used in this mapping.
	 */
	public AbstractVisualMappingFunction(final String columnName, final Class<K> columnType, final VisualProperty<V> vp, final CyEventHelper eventHelper) {
		this.columnType = columnType;
		this.columnName = columnName;
		this.vp = vp;
		this.eventHelper = eventHelper;
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
	public void apply(final CyRow row, final View<? extends CyIdentifiable> view) {
		final V value = getMappedValue(row);
		
		if (value != null)
			view.setVisualProperty(vp, value);
	}
}

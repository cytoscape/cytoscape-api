package org.cytoscape.view.vizmap.mappings;

/*
 * #%L
 * Cytoscape VizMap API (vizmap-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
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
import org.cytoscape.view.vizmap.VisualMappingFunction;
import org.cytoscape.view.vizmap.events.VisualMappingFunctionChangeRecord;
import org.cytoscape.view.vizmap.events.VisualMappingFunctionChangedEvent;
import org.cytoscape.view.vizmap.mappings.BoundaryRangeValues;

/**
 * Encapsulates a ContinuousMapping Point with a single point value and
 * associated {@link BoundaryRangeValues}.
 * 
 * @param <K>
 *            Generic type of the attribute mapped.
 * @param <V>
 *            The generic type of associated {@link BoundaryRangeValues}.
 * @CyAPI.Final.Class
 * @CyAPI.InModule vizmap-api
 */
public final class ContinuousMappingPoint<K, V> {
	private K value;
	private BoundaryRangeValues<V> range;

	private final ContinuousMapping<K, V> parentMapping;
	private final CyEventHelper eventHelper;

	/**
	 * Constructor.
	 * 
	 * @param value
	 *            double.
	 * @param range
	 *            BoundaryRangeValues object.
	 */
	public ContinuousMappingPoint(K value, BoundaryRangeValues<V> range, final ContinuousMapping<K, V> parentMapping,
			final CyEventHelper eventHelper) {
		if (value instanceof Number == false)
			throw new IllegalArgumentException("Value must be a number.");

		this.value = value;
		this.range = range;
		this.parentMapping = parentMapping;
		this.eventHelper = eventHelper;
	}

	/**
	 * Gets Point Value.
	 * 
	 * @return double value.
	 */
	public K getValue() {
		return value;
	}

	/**
	 * Sets Point Value.
	 * 
	 * @param value
	 *            double value.
	 */
	public void setValue(K value) {
		this.value = value;
		eventHelper.addEventPayload((VisualMappingFunction) parentMapping, new VisualMappingFunctionChangeRecord(),
				VisualMappingFunctionChangedEvent.class);
	}

	/**
	 * Gets BoundaryRangeValues.
	 * 
	 * @return BoundaryRangeValues Object.
	 */
	public BoundaryRangeValues<V> getRange() {
		return range;
	}

	/**
	 * Sets BoundaryRangeValues.
	 * 
	 * @param range
	 *            BoundaryRangeValues Object.
	 */
	public void setRange(BoundaryRangeValues<V> range) {
		this.range = range;
		eventHelper.addEventPayload((VisualMappingFunction) parentMapping, new VisualMappingFunctionChangeRecord(),
				VisualMappingFunctionChangedEvent.class);
	}

}

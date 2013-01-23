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

import java.util.List;

import org.cytoscape.view.vizmap.VisualMappingFunction;

/**
 * An interface describing a continuous mapping from attribute value
 * to visual property.
 * @param <K> Generic type of the attribute mapped.
 * @param <V> Generic type of the VisualProperty used in this mapping.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule vizmap-api
 */
public interface ContinuousMapping<K, V> extends VisualMappingFunction<K,V>{

	/** A label string for this mapping. */
	public static final String CONTINUOUS = "Continuous Mapping";

	/**
	 * Gets all Data Points.
	 * 
	 * @return List of ContinuousMappingPoint objects.
	 */
	public List<ContinuousMappingPoint<K, V>> getAllPoints();

	/**
	 * Adds a New Data Point.
	 */
	public void addPoint(K value, BoundaryRangeValues<V> brv);

	/**
	 * Removes a Point from the List.
	 * @param index The index of the Point to remove.
	 */
	public void removePoint(int index);

	/**
	 * Gets Total Point Count.
	 * @return the total point count.
	 */
	public int getPointCount();

	/**
	 * Gets Specified Point.
	 *  
	 * @param index Index Value.
	 * @return ContinuousMappingPoint.
	 */
	public ContinuousMappingPoint<K, V> getPoint(int index);

}

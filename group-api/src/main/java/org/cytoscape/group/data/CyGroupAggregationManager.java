package org.cytoscape.group.data;

/*
 * #%L
 * Cytoscape Groups API (group-api)
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

import org.cytoscape.group.CyGroup;
import org.cytoscape.model.CyColumn;
import org.cytoscape.model.CyTable;

import java.util.List;
  
/**
 * The CyGroupAggregationManager provides a mechanism to add new
 * Aggregators to the group aggregation mechanism.  It is
 * meant to be used by apps that want to extend the aggregation
 * mechanism for types not supported by the built-in aggregators.
 */
public interface CyGroupAggregationManager {
	/**
 	 * Add a new aggregator to the available aggregators.  Aggregators
 	 * provide a specific aggregation algorithm for a specific CyColumn
 	 * type.
 	 *
 	 * @param aggregator the {@link Aggregator} 
 	 */
	void addAggregator(Aggregator aggregator);

	/**
 	 * Remove an aggregator from the available aggregators.
 	 *
 	 * @param aggregator the {@link Aggregator} to remove
 	 */
	void removeAggregator(Aggregator aggregator);

	/**
 	 * Get the list of aggregators for a particular Class.
 	 *
 	 * @param type the {@link Class} to get aggregators for
 	 * @return the list of aggregators
 	 */
	List<Aggregator> getAggregators(Class type);

	/**
 	 * Get the list of aggregators. 
 	 *
 	 * @return the list of aggregators
 	 */
	List<Aggregator> getAggregators();

	/**
 	 * Get the list of classes for which have aggregators.
 	 *
 	 * @return the list of classes we're aggregating
 	 */
	List<Class> getSupportedClasses();
} 

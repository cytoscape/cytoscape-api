package org.cytoscape.group.data;

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

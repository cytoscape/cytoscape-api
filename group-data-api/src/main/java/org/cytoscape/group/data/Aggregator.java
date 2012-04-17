package org.cytoscape.group.data;

import org.cytoscape.group.CyGroup;
import org.cytoscape.model.CyColumn;
import org.cytoscape.model.CyTable;

/**
 * The Aggregator interface acts as a service interface for
 * different methods of aggregating attribute data for the
 * nodes in a {@link CyGroup}.
 */
public interface Aggregator<T> {
	/**
 	 * Return the Class this aggregator supports
 	 *
 	 * @return the {@link Class} supported
 	 */
	public Class getSupportedType();

	/**
 	 * This gets the name of the handling type supported
 	 * by this aggregator, suitable for use in a menu.  
 	 *
 	 * @return a name for the handling type
 	 */
	public String toString();

	/**
	 * Aggregate the column for the members of the specified group into
	 * the group.
	 *
	 * @param table the CyTable for this group
	 * @param group the group
	 * @param column the column we're aggregating
	 * @return the aggregated value
	 */
  public <T> T aggregate(CyTable table, CyGroup group, CyColumn column);
    
} 


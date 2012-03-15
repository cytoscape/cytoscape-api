package org.cytoscape.group.data;

import org.cytoscape.group.CyGroup;
import org.cytoscape.model.CyColumn;
import org.cytoscape.model.CyTable;
  
public interface Aggregator<T> {
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


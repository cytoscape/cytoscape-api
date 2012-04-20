package org.cytoscape.task;

import org.cytoscape.model.CyColumn;

/**
 * A TableCellTaskFactory that is always ready to produce a TaskIterator.
 * @CyAPI.Abstract.Class
 */
public abstract class AbstractTableCellTaskFactory implements TableCellTaskFactory {
	/**
	 * Always returns true.
	 * @param column The table column. 
	 * @param primaryKeyValue the value of the primary key 
	 * @return always returns true.
	 */
	@Override
	public boolean isReady(CyColumn column, Object primaryKeyValue) {
		return true;
	}
}

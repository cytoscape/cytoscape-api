package org.cytoscape.task;

import org.cytoscape.model.CyColumn;

/**
 * A TableCellTaskFactory that is always ready to produce a TaskIterator.
 * @CyAPI.Abstract.Class
 */
public abstract class AbstractTableCellTaskFactory implements TableCellTaskFactory {
	/**
	 * Returns true if the supplied column and value are not null.
	 * @param column The table column. 
	 * @param primaryKeyValue the value of the primary key 
	 * @return true if the supplied column and value are not null.
	 */
	@Override
	public boolean isReady(CyColumn column, Object primaryKeyValue) {
		return column != null && primaryKeyValue != null;
	}
}

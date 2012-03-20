package org.cytoscape.task;

import org.cytoscape.model.CyColumn;

/**
 * A TableCellTaskFactory that is always ready to produce a TaskIterator.
 * @CyAPI.Abstract.Class
 */
public abstract class AbstractTableCellTaskFactory implements TableCellTaskFactory {
	@Override
	public boolean isReady(CyColumn column, Object primaryKeyValue) {
		return true;
	}
}

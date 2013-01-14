package org.cytoscape.task;

import org.cytoscape.model.CyTable;

/**
 * A TableTaskFactory that is always ready to produce a TaskIterator.
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule core-task-api
 */
public abstract class AbstractTableTaskFactory implements TableTaskFactory {
	/**
	 * Returns true if the supplied table is not null.
	 * @param table The table. 
	 * @return true if the supplied table is not null.
	 */
	@Override
	public boolean isReady(CyTable table) {
		return table != null;
	}
}

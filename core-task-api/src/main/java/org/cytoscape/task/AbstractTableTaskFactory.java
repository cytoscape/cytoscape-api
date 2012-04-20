package org.cytoscape.task;

import org.cytoscape.model.CyTable;

/**
 * A TableTaskFactory that is always ready to produce a TaskIterator.
 * @CyAPI.Abstract.Class
 */
public abstract class AbstractTableTaskFactory implements TableTaskFactory {
	/**
	 * Always returns true.
	 * @param table The table. 
	 * @return always returns true.
	 */
	@Override
	public boolean isReady(CyTable table) {
		return true;
	}
}

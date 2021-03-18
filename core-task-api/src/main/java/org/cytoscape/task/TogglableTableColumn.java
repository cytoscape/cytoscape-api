package org.cytoscape.task;

import org.cytoscape.model.CyColumn;

/**
 * This interface is meant to be implemented by task factories that provides tasks which switches
 * a {@link CyColumn} feature on and off.
 * Useful when you want your task factory (probably a {@link TableColumnTaskFactory}) to be displayed
 * as a toggle button or a check box menu item, for instance.
 * 
 * @see org.cytoscape.work.Togglable
 * @see TableColumnTaskFactory
 */
public interface TogglableTableColumn {
	
	/**
	 * Returns <code>true</code> if the function provided by a task factory is on
	 * for the passed column and <code>false</code> if it is off.
	 * 
	 * @param column The {@link CyColumn} required by the task.
	 */
	boolean isOn(CyColumn column);

}

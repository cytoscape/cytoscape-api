package org.cytoscape.task.edit;

import org.cytoscape.model.CyColumn;
import org.cytoscape.task.TableColumnTaskFactory;
import org.cytoscape.work.TaskIterator;


/**
 * This interface provides a task iterator for renaming a column.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule core-task-api
 */
public interface RenameColumnTaskFactory extends TableColumnTaskFactory {

	/**
	 * Create a task iterator for renaming a selected column. The created task
	 * will run synchronously in the current thread and will not create a task monitor.
	 * @param column The selected column for renaming.
	 * @param newColumnName The new name for the selected column.
	 * @return a task iterator of type {@link TaskIterator}.
	 */
	TaskIterator createTaskIterator(CyColumn column, String newColumnName);
}

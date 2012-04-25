package org.cytoscape.task.edit;

import org.cytoscape.model.CyTable;
import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskIterator;


/**
 * This interface provides a task iterator for mapping a global to a local table.
 * @CyAPI.Api.Interface
 */
public interface MapGlobalToLocalTableTaskFactory extends TaskFactory{

	/**
	 * Creates a task iterator for mapping a selected global table to a selected local table.
	 * The created task will run synchronously in the current thread and will not create a 
	 * new task monitor.
	 * @param globalTable The global table to map from. 
	 * @param localTable The local table to map to. 
	 * @return A task iterator of type {@link TaskIterator}.
	 */
	TaskIterator createTaskIterator(final CyTable globalTable, final CyTable localTable);
}

package org.cytoscape.task.table;

import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskIterator;


/**
 * This interface provides a task iterator for mapping a global to a local table.
 * @author rozagh
 *
 */
public interface MapGlobalToLocalTableTaskFactory extends TaskFactory{

	/**
	 * Creates a task iterator for mapping a selected global table to a selected local table.
	 * The created task will run synchronously in the current thread and will not create a 
	 * new task monitor.
	 * @param globalTable The global table name to map from. 
	 * @param localTable The local table name to map to. 
	 * @return A task iterator of type {@link TaskIterator}.
	 */
	TaskIterator createTaskIterator(final String globalTable, final String localTable);
}

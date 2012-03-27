package org.cytoscape.task.table;

import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskIterator;
import org.cytoscape.work.util.ListSingleSelection;


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
	 * @param globalTables A list of global tables with a single table selected from.
	 * @param localTables A list of local tables with a single table selected from to map to.
	 * @return A task iterator of type {@link TaskIterator}.
	 */
	TaskIterator createTaskIterator(final ListSingleSelection<String> globalTables, final ListSingleSelection<String> localTables);
}

package org.cytoscape.task.edit;

import java.util.Collection;

import org.cytoscape.model.CyTable;
import org.cytoscape.task.TableTaskFactory;
import org.cytoscape.work.TaskIterator;


/**
 * This interface provides a task iterator for mapping a global to a local table.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule core-task-api
 */
public interface MapGlobalToLocalTableTaskFactory extends TableTaskFactory{

	/**
	 * Creates a task iterator for mapping a selected global table to a selected local table.
	 * The created task will run synchronously in the current thread and will not create a 
	 * new task monitor.
	 * @param globalTable The global table to map from. 
	 * @param localTable The local table to map to. 
	 * @return A task iterator of type {@link TaskIterator}.
	 */
	TaskIterator createTaskIterator(final CyTable globalTable, final Collection<CyTable> localTables);

}

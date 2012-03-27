package org.cytoscape.task.export.table;

import org.cytoscape.model.CyTable;
import org.cytoscape.task.TableTaskFactory;
import org.cytoscape.work.TaskIterator;
import org.cytoscape.work.util.ListSingleSelection;

/**
 * This interface provides a task iterator for exporting the current 
 * table.
 * @author rozagh
 *
 */
public interface ExportCurrentTableTaskFactory extends TableTaskFactory {
	
	/**
	 * Creates a task iterator for exporting the current table to a selected table.
	 * This task will run synchronously in the current thread and it will not create a
	 * task monitor.
	 * @param table The current table
	 * @param selectTable A list of tables to select from.
	 * @return task iterator
	 */
	TaskIterator createTaskIterator(CyTable table, ListSingleSelection<String> selectTable);

}

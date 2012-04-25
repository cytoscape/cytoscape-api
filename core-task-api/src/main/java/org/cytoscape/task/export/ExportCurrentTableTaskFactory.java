package org.cytoscape.task.export;

import org.cytoscape.model.CyTable;
import org.cytoscape.task.TableTaskFactory;
import org.cytoscape.work.TaskIterator;

/**
 * This interface provides a task iterator for exporting the current 
 * table.
 * @CyAPI.Api.Interface
 *
 */
public interface ExportCurrentTableTaskFactory extends TableTaskFactory {
	
	/**
	 * Creates a task iterator for exporting the current table to a selected table.
	 * This task will run synchronously in the current thread and it will not create a
	 * task monitor.
	 * @param table The table to be exported.
	 * @param format The file format description (e.g. .csv, .tsv) 
	 * @return task iterator
	 */
	TaskIterator createTaskIterator(CyTable table, String format);

}

package org.cytoscape.task.write;

import java.io.File;

import org.cytoscape.model.CyTable;
import org.cytoscape.task.TableTaskFactory;
import org.cytoscape.work.TaskIterator;

/**
 * This task factory provides a task iterator for writing a specified
 * table to a specified file.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule core-task-api
 */
public interface ExportTableTaskFactory extends TableTaskFactory {
	
	/**
	 * Returns a task factory that write the specified table to the specified file.
	 * @param table The table to be written.
	 * @param file The file the table will be written to.
	 * @return a task factory that write the specified table to the specified file.
	 */
	public TaskIterator createTaskIterator(CyTable table, File file);
}

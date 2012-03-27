package org.cytoscape.task.export.table;

import java.io.File;

import org.cytoscape.task.NetworkViewTaskFactory;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.work.TaskIterator;

/**
 * This interface provides a task iterator for exporting the edge table.
 * @author rozagh
 *
 */
public interface ExportEdgeTableTaskFactory extends NetworkViewTaskFactory{

	/**
	 * Creates a task iterator to export the edge table for a selected view. 
	 * The created task runs synchronously in the current thread and does not
	 * create a task monitor.
	 * @param view The view of the edge table to be exported.
	 * @param outputFile The file to store the result of the export in.
	 * @return A task iterator of type {@link TaskIterator}.
	 */
	TaskIterator createTaskIterator(CyNetworkView view, final File outputFile);

}

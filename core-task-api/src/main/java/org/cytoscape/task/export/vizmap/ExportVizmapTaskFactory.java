package org.cytoscape.task.export.vizmap;

import java.io.File;

import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskIterator;

/**
 * This interface provides a task iterator for exporting the vizmap visual style.
 * @author rozagh
 *
 */
public interface ExportVizmapTaskFactory extends TaskFactory{

	/**
	 * Creates a task iterator for exporting the vizmap file.
	 * The created task will run synchronously in the thread and does
	 * not create a task monitor.
	 * @param file The file to store the export output in.
	 * @return a task iterator of type {@link TaskIterator}.
	 */
	TaskIterator creatTaskIterator(final File file);
}

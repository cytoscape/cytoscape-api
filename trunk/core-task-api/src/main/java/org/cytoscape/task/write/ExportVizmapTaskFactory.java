package org.cytoscape.task.write;

import java.io.File;

import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskIterator;

/**
 * This interface provides a task iterator for exporting the vizmap visual style.
 * @CyAPI.Api.Interface
 */
public interface ExportVizmapTaskFactory extends TaskFactory{

	/**
	 * Creates a task iterator for exporting the vizmap file.
	 * @param file The file to store the export output in.
	 * @return a task iterator of type {@link TaskIterator}.
	 */
	TaskIterator creatTaskIterator(final File file);
}

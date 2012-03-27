package org.cytoscape.task.loaddatatable;

import java.net.URL;

import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskIterator;


/**
 * This interface provides a task iterator for loading an attribute URL to a table.
 * @author rozagh
 *
 */
public interface LoadAttributesURLTaskFactory extends TaskFactory {
	
	/**
	 * Creates a task iterator for loading an attribute URL to the global table. The created task
	 * runs synchronously in the current thread and does not create a task monitor.
	 * @param url The attribute URL to load into the global table.
	 * @return a task iterator of type {@link TaskIterator}.
	 */
	TaskIterator createTaskIterator(final URL url);


}

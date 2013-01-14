
package org.cytoscape.task.read;

import org.cytoscape.view.vizmap.VisualStyle;
import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskIterator;

import java.util.Set;
import java.io.File;
import java.io.InputStream;

/**
 * An interface for loading styles from files. 
 * This interface also provides a task iterator for loading files into visual styles.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule core-task-api
 */
public interface LoadVizmapFileTaskFactory extends TaskFactory{

	/**
	 * Return a set of VisualStyle objects read from the specified file. 
	 * @param f The file containing visual styles to be read.
	 * @return a set of VisualStyle objects read from the specified file. 
	 */
	Set<VisualStyle> loadStyles(File f);
	

	/**
	 * Return a set of VisualStyle objects read from the specified file. 
	 * @param is The inputStream containing visual styles to be read.
	 * @return a set of VisualStyle objects read from the specified file. 
	 */
	Set<VisualStyle> loadStyles(InputStream is);

	/**
	 * Creates a task iterator for loading files into visual styles.
	 * The created task runs synchronously in the current thread and does not
	 * create a task monitor.
	 * @param file The file containing visual styles to be read.
	 * @return A task iterator of type {@link TaskIterator}.
	 */
	TaskIterator createTaskIterator(File file);
}

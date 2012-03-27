package org.cytoscape.task.select;

import java.io.File;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.task.NetworkTaskFactory;
import org.cytoscape.work.TaskIterator;

/**
 * This interface provides a task iterator for selecting items based on a file.
 * @author rozagh
 *
 */
public interface SelectFromFileListTaskFactory extends NetworkTaskFactory {
	
	/**
	 * Creates a task iterator for selecting items of a network based on a file.
	 * @param network The target network to select nodes and edges from.
	 * @param file The file that has the selection list in.
	 * @return a task iterator of type {@link TaskIterator}.
	 */
	TaskIterator createTaskIterator(CyNetwork network, final File file);


}

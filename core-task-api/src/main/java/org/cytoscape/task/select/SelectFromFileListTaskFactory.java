package org.cytoscape.task.select;

import java.io.File;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.task.NetworkTaskFactory;
import org.cytoscape.work.TaskIterator;

/**
 * This interface provides a task iterator for selecting items based on a file.
 * The file should be a simple text file with one identifier per line.
 * @CyAPI.Api.Interface
 */
public interface SelectFromFileListTaskFactory extends NetworkTaskFactory {
	
	/**
	 * Creates a task iterator for selecting items of a network based on a file.
	 * @param network The target network to select nodes and edges from.
	 * @param file The file that has the selection list in.
 	 * The file should be a simple text file with one identifier per line.
	 * @return a task iterator of type {@link TaskIterator}.
	 */
	TaskIterator createTaskIterator(CyNetwork network, final File file);

}

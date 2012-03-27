package org.cytoscape.task.title;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.task.NetworkTaskFactory;
import org.cytoscape.work.TaskIterator;


/**
 * This interface provides a task iterator for editing a network's title.
 * @author rozagh
 *
 */
public interface EditNetworkTitleTaskFactory extends NetworkTaskFactory{
	
	/**
	 * Creates a task iterator for editing a network's title.
	 * The created task will run synchronously in the current 
	 * thread and will not create a task monitor.
	 * @param network The network to edit the name of.
	 * @param title The new network title.
	 * @return a task ietrator of type {@link TaskIterator}.
	 */
	TaskIterator createTaskIterator (CyNetwork network, String title);
}

package org.cytoscape.task.loadnetwork;

import java.net.URL;
import java.util.Set;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.work.AbstractTaskFactory;
import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskIterator;

/**
 * This interface provides a task iterator for loading a URL into a network.
 * @author rozagh
 *
 */
public interface LoadNetworkURLTaskFactory extends TaskFactory{
	
	/**
	 * Creates a task iterator for loading a URL into a network. The created task runs
	 * synchronously in the current thread and does not create a task monitor.
	 * @param url the URL for loading into the network.
	 * @return a task iterator of type {@link TaskIterator}.
	 */
	TaskIterator loadCyNetworks(final URL url);

}

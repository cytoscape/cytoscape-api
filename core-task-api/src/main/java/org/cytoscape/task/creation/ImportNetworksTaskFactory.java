package org.cytoscape.task.creation;

import java.net.URL;
import java.util.Set;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.work.TaskIterator;

public interface ImportNetworksTaskFactory {
	
	TaskIterator loadCyNetworks(final URL url);

}

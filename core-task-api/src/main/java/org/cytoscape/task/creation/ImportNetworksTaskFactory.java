package org.cytoscape.task.creation;

import java.net.URL;
import java.util.Set;

import org.cytoscape.model.CyNetwork;

public interface ImportNetworksTaskFactory {
	
	Set<CyNetwork> loadCyNetworks(final URL url);

}

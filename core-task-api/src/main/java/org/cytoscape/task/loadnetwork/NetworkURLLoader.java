package org.cytoscape.task.loadnetwork;

import java.net.URL;
import java.util.Set;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.work.AbstractTaskFactory;
import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskIterator;

public interface NetworkURLLoader extends TaskFactory{
	
	TaskIterator loadCyNetworks(final URL url);

}

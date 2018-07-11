package org.cytoscape.task.hide;

import java.util.Collection;

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.work.TaskIterator;

public interface UnHideTaskFactory {

	TaskIterator createTaskIterator(CyNetworkView view, Collection<CyNode> nodes, Collection<CyEdge> edges);
	
}

package org.cytoscape.task.hide;

import java.util.Collection;

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.work.TaskIterator;

/**
 * This interface provides a task iterator for hiding the given nodes and edges.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule core-task-api
 */
public interface HideTaskFactory {

	/**
	 * Creates a task iterator that will hide the given nodes and edges.
	 * @param view The CyNetworkView to that contains the nodes and edges
	 * @param nodes A collection of nodes to hide, may be null.
	 * @param edges A collection of edges to hide, may be null.
	 */
	TaskIterator createTaskIterator(CyNetworkView view, Collection<CyNode> nodes, Collection<CyEdge> edges);
	
}

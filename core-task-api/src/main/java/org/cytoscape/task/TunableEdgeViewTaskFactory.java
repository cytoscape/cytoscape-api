package org.cytoscape.task;

import org.cytoscape.model.CyEdge;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;

public interface TunableEdgeViewTaskFactory<T> extends EdgeViewTaskFactory {
	T createTunableContext(View<CyEdge> edgeView, CyNetworkView networkView);
}

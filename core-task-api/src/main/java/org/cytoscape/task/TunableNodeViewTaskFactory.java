package org.cytoscape.task;

import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;

public interface TunableNodeViewTaskFactory<T> extends NodeViewTaskFactory {
	T createTunableContext(View<CyNode> nodeView, CyNetworkView networkView);
}

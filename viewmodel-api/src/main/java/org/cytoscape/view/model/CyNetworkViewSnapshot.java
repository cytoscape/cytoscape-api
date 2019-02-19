package org.cytoscape.view.model;

import java.util.Collection;

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.spacial.SpacialIndex2D;

public interface CyNetworkViewSnapshot extends CyNetworkView {
	
	CyNetworkView getMutableNetworkView();
	
	SpacialIndex2D getSpacialIndex2D();
	
	
	int getNodeCount();

	int getEdgeCount();
	
	
	View<CyNode> getNodeView(long suid);
	
	View<CyEdge> getEdgeView(long suid);
	
	
	Collection<View<CyNode>> getSelectedNodes();
	
	
	Iterable<View<CyEdge>> getAdjacentEdgeIterable(View<CyNode> node);
	
	Iterable<View<CyEdge>> getAdjacentEdgeIterable(long nodeSuid);
	
	
	SnapshotEdgeInfo getEdgeInfo(View<CyEdge> edge);
	
	SnapshotNodeInfo getNodeInfo(View<CyNode> node);
	
	
	<T> T getViewDefault(VisualProperty<T> vp);
}

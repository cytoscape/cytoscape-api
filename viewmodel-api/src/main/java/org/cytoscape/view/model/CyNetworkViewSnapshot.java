package org.cytoscape.view.model;

import java.util.Collection;

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.spacial.SpacialIndex2D;

public interface CyNetworkViewSnapshot extends CyNetworkView {
	
	CyNetworkView getMutableNetworkView();
	
	// MKTODO change method name to getNodeSpacialIndex2D();
	SpacialIndex2D<Long> getSpacialIndex2D();
	
	
	int getNodeCount();

	int getEdgeCount();
	
	
	Collection<View<CyNode>> getTrackedNodes(Object key);
	
	Collection<View<CyEdge>> getTrackedEdges(Object key);
	
	
	Iterable<View<CyEdge>> getAdjacentEdgeIterable(View<CyNode> node);
	
	Iterable<View<CyEdge>> getAdjacentEdgeIterable(long nodeSuid);
	
	
	SnapshotEdgeInfo getEdgeInfo(View<CyEdge> edge);
	
	SnapshotNodeInfo getNodeInfo(View<CyNode> node);
	
	
	<T> T getViewDefault(VisualProperty<T> vp);
}

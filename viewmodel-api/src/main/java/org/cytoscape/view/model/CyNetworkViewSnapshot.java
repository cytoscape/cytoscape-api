package org.cytoscape.view.model;

import java.util.Collection;

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.spacial.SpacialIndex2D;

public interface CyNetworkViewSnapshot extends ReadableNetworkView {
	
	CyNetworkView getMutableNetworkView();
	
	SpacialIndex2D getSpacialIndex2D();
	
	
	int getNodeCount();

	int getEdgeCount();
	
	
	ReadableView<CyNode> getNodeView(long suid);
	
	ReadableView<CyEdge> getEdgeView(long suid);
	
	
	Collection<ReadableView<CyNode>> getSelectedNodes();
	
	
	Iterable<ReadableView<CyEdge>> getAdjacentEdgeIterable(ReadableView<CyNode> node);
	
	
	SnapshotEdgeInfo getEdgeInfo(ReadableView<CyEdge> edge);
	
}

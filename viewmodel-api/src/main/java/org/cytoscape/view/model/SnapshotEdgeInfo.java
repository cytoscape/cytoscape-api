package org.cytoscape.view.model;

import org.cytoscape.model.CyNode;

public interface SnapshotEdgeInfo {
	
	Long getSUID();
	
	long getSourceSUID();
	
	ReadableView<CyNode> getSourceNodeView();
	
	long getTargetSUID();
	
	ReadableView<CyNode> getTargetNodeView();
	
	boolean isDirected();

}

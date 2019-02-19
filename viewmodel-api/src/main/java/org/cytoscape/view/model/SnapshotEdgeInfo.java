package org.cytoscape.view.model;

import org.cytoscape.model.CyNode;

public interface SnapshotEdgeInfo {
	
	Long getSUID();
	
	Long getModelSUID();
	
	long getSourceViewSUID();
	
	ReadableView<CyNode> getSourceNodeView();
	
	long getTargetViewSUID();
	
	ReadableView<CyNode> getTargetNodeView();
	
	boolean isDirected();

}

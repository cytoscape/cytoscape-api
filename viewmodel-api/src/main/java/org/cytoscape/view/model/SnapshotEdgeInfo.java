package org.cytoscape.view.model;

import org.cytoscape.model.CyNode;

public interface SnapshotEdgeInfo {
	
	Long getSUID();
	
	Long getModelSUID();
	
	long getSourceViewSUID();
	
	View<CyNode> getSourceNodeView();
	
	long getTargetViewSUID();
	
	View<CyNode> getTargetNodeView();
	
	boolean isDirected();

}

package org.cytoscape.view.model;

public interface SnapshotEdgeInfo {
	
	Long getSUID();
	
	long getSourceSUID();
	
	long getTargetSUID();
	
	boolean isDirected();
	
//	boolean isSelected();

}

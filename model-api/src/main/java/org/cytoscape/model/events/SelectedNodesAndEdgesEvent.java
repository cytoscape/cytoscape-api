package org.cytoscape.model.events;

import java.util.Collection;

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyTableUtil;


public class SelectedNodesAndEdgesEvent extends AbstractCyEvent<CyNetwork> {
	
	private Collection<CyNode> selectedNodes;
	private Collection<CyNode> unselectedNodes;
	private Collection<CyEdge> selectedEdges;
	private Collection<CyEdge> unselectedEdges;
	
	private final boolean isCurrentNetwork;
	private final boolean nodesChanged;
	private final boolean edgesChanged;

	
	public SelectedNodesAndEdgesEvent(CyNetwork source, boolean isCurrentNetwork, boolean nodesChanged, boolean edgesChanged) {
		super(source, SelectedNodesAndEdgesListener.class);
		this.isCurrentNetwork = isCurrentNetwork;
		this.nodesChanged = nodesChanged;
		this.edgesChanged = edgesChanged;
	}

	public CyNetwork getNetwork() {
		return getSource();
	}
	
	public boolean isCurrentNetwork() {
		return isCurrentNetwork;
	}
	
	public boolean nodesChanged() {
		return nodesChanged;
	}
	
	public boolean edgesChanged() {
		return edgesChanged;
	}
	
	public Collection<CyNode> getSelectedNodes() {
		if(selectedNodes == null) {
			selectedNodes = CyTableUtil.getNodesInState(getNetwork(), CyNetwork.SELECTED, true);
		}
		return selectedNodes;
	}
	
	public Collection<CyNode> getUnselectedNodes() {
		if(unselectedNodes == null) {
			unselectedNodes = CyTableUtil.getNodesInState(getNetwork(), CyNetwork.SELECTED, false);
		}
		return unselectedNodes;
	}
	
	public Collection<CyEdge> getSelectedEdges() {
		if(selectedEdges == null) {
			selectedEdges = CyTableUtil.getEdgesInState(getNetwork(), CyNetwork.SELECTED, true);
		}
		return selectedEdges;
	}
	
	public Collection<CyEdge> getUnselectedEdges() {
		if(unselectedEdges == null) {
			unselectedEdges = CyTableUtil.getEdgesInState(getNetwork(), CyNetwork.SELECTED, false);
		}
		return unselectedEdges;
	}

}

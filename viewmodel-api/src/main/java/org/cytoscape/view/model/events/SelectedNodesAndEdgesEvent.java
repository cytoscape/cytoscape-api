package org.cytoscape.view.model.events;

import java.util.ArrayList;
import java.util.Collection;

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyTableUtil;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;

public class SelectedNodesAndEdgesEvent extends AbstractCyEvent<CyNetworkView> {
	
	private Collection<CyNode> nodes;
	private Collection<CyEdge> edges;
	
	private Collection<View<CyNode>> nodeViews;
	private Collection<View<CyEdge>> edgeViews;
	
	
	public SelectedNodesAndEdgesEvent(CyNetworkView source) {
		super(source, SelectedNodesAndEdgesListener.class);
	}
	
	public CyNetworkView getNetworkView() {
		return getSource();
	}
	
	public CyNetwork getNetwork() {
		return getSource().getModel();
	}
	
	public Collection<CyNode> getNodes() {
		if(nodes == null) {
			nodes = CyTableUtil.getNodesInState(getNetwork(), CyNetwork.SELECTED, true);
		}
		return nodes;
	}
	
	public Collection<CyEdge> getEdges() {
		if(edges == null) {
			edges = CyTableUtil.getEdgesInState(getNetwork(), CyNetwork.SELECTED, true);
		}
		return edges;
	}
	
	public Collection<View<CyNode>> getNodeViews() {
		if(nodeViews == null) {
			Collection<CyNode> nodes = getNodes();
			nodeViews = new ArrayList<>(nodes.size());
			CyNetworkView networkView = getNetworkView();
			for(CyNode node : nodes) {
				View<CyNode> nodeView = networkView.getNodeView(node);
				if(nodeView != null) {
					nodeViews.add(nodeView);
				}
			}
		}
		return nodeViews;
	}
	
	public Collection<View<CyEdge>> getEdgeViews() {
		if(edgeViews == null) {
			Collection<CyEdge> edges = getEdges();
			edgeViews = new ArrayList<>(edges.size());
			CyNetworkView networkView = getNetworkView();
			for(CyEdge edge : edges) {
				View<CyEdge> edgeView = networkView.getEdgeView(edge);
				if(edgeView != null) {
					edgeViews.add(edgeView);
				}
			}
		}
		return edgeViews;
	}
	

}

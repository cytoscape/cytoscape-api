package org.cytoscape.model.events;

import java.util.Collection;

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyTableUtil;


/**
 * Event that signals when the selection state of nodes and/or edges has changed within a network.
 * 
 * <p>
 * This is a convenience API. The traditional way to detect node/edge selection is to use a {@link RowsSetEvent}.
 * However that event fires for every change to a table, not just selection change, and requires the listener
 * to map the rows that changed back to nodes and edges. This event provides a more convenient API for listeners that just want
 * to be signaled about selection changes and provides direct access to the CyNode and CyEdge objects. 
 * </p>
 * 
 * <p>
 * Note: Node selection is stored in the default node table, and edge selection is stored in the default edge table,
 * meaning that node selection and edge selection have different event sources. This results in two of these events
 * being fired when both node and edge selection changes. It is safe to handle both events. If the listener only
 * cares about node events this is indicated by the {@link SelectedNodesAndEdgesEvent#nodesChanged} method. 
 * </p>
 * 
 * <p>
 * Note: The event source for this event is the CyNetwork, not the default node or edge table.
 * </p>
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule model-api
 */
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

	/**
	 * Returns the CyNetwork event source.
	 */
	public CyNetwork getNetwork() {
		return getSource();
	}
	
	/**
	 * Returns true if the network event source is the current network.
	 */
	public boolean isCurrentNetwork() {
		return isCurrentNetwork;
	}

	/**
	 * Returns true if the selection state of nodes has changed.
	 */
	public boolean nodesChanged() {
		return nodesChanged;
	}

	/**
	 * Returns true if the selection stated of edges has changed.
	 */
	public boolean edgesChanged() {
		return edgesChanged;
	}
	
	
	/**
	 * Returns all the nodes in the network that are currently selected.
	 */
	public Collection<CyNode> getSelectedNodes() {
		if(selectedNodes == null) {
			selectedNodes = CyTableUtil.getNodesInState(getNetwork(), CyNetwork.SELECTED, true);
		}
		return selectedNodes;
	}
	
	/**
	 * Returns all the nodes in the network that are currently unselected.
	 */
	public Collection<CyNode> getUnselectedNodes() {
		if(unselectedNodes == null) {
			unselectedNodes = CyTableUtil.getNodesInState(getNetwork(), CyNetwork.SELECTED, false);
		}
		return unselectedNodes;
	}
	
	/**
	 * Returns all the edges in the network that are currently selected.
	 */
	public Collection<CyEdge> getSelectedEdges() {
		if(selectedEdges == null) {
			selectedEdges = CyTableUtil.getEdgesInState(getNetwork(), CyNetwork.SELECTED, true);
		}
		return selectedEdges;
	}
	
	/**
	 * Returns all the edges in the network that are currently unselected.
	 */
	public Collection<CyEdge> getUnselectedEdges() {
		if(unselectedEdges == null) {
			unselectedEdges = CyTableUtil.getEdgesInState(getNetwork(), CyNetwork.SELECTED, false);
		}
		return unselectedEdges;
	}

}

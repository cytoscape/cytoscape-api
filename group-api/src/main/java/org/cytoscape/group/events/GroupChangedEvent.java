package org.cytoscape.group.events;

import java.util.List;

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.group.CyGroup;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyTableEntry;

/**
 * This event signals that a group has changed, either by adding or
 * removing nodes or edges.
 *
 * @CyAPI.Final.Class
 */
public final class GroupChangedEvent extends AbstractCyEvent<CyGroup> {
	public enum ChangeType {
		NODE_ADDED, INTERNAL_EDGE_ADDED, EXTERNAL_EDGE_ADDED,
		NODES_ADDED, NODES_REMOVED, EDGES_ADDED, EDGES_REMOVED }

	private CyTableEntry whatChanged;
	private List<CyNode> nodeList;
	private ChangeType change;

	/**
	 * Constructs event.
	 * @param source the {@link CyGroup} that has been changed.
	 * @param whatChanged the {@link CyNode} or {@link CyEdge} or list of {@link CyNodes} that were added or removed.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public GroupChangedEvent(final CyGroup source, final Object whatChanged, ChangeType change) {
		super(source, GroupChangedListener.class);

		this.whatChanged = null;
		this.change = change;
		this.nodeList = null;

		// If this is NODES_ADDED or REMOVED, then the "whatChanged" argument must be a Collection
		if (change == ChangeType.NODES_ADDED || change == ChangeType.NODES_REMOVED) {
			if ((whatChanged instanceof List) && (((List)whatChanged).get(0) instanceof CyNode)) {
				this.nodeList = (List<CyNode>) whatChanged;
			} else
				throw new IllegalArgumentException("the \"whatChanged\" parameter must be a list of nodes for NODES_ADDED or NODES_REMOVED!");
		} else if ((whatChanged instanceof CyEdge) ||
		           (whatChanged instanceof CyNode)) {
			this.whatChanged = (CyTableEntry) whatChanged;
		} else {
			throw new IllegalArgumentException("the \"whatChanged\" parameter must be a node or an edge!");
		}
			
	}

	/**
 	 * Get the object that changed.  This should either be a CyEdge or a CyNode.
 	 *
 	 * @return the CyTableEntry (CyNode or CyEdge) that was added or removed.
 	 */
	public CyTableEntry getChangedObject() {
		return whatChanged;
	}

	/**
 	 * Get the list of nodes that changed.  
 	 *
 	 * @return the list of CyNodes that were added or removed.
 	 */
	public List<CyNode> getChangedNodes() {
		return nodeList;
	}

	/**
	 * Was the change an add or a removal from the group.
	 *
	 * @return true if the node or edge was added.
	 */
	public ChangeType getChangeType() {
		return change;
	}
}

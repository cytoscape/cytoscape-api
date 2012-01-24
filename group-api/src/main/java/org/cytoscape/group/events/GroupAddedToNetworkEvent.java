package org.cytoscape.group.events;


import org.cytoscape.group.CyGroupManager;
import org.cytoscape.group.CyGroup;
import org.cytoscape.model.CyNetwork;


/**
 * This event signals that an existing group has been added to a new network.
 * This is meant to be used primarily by the view and presentation layers to
 * allow them to appropriately visualize the group.
 *
 * @CyAPI.Final.Class
 */
public final class GroupAddedToNetworkEvent extends AbstractGroupEvent {
	/**
	 * Constructs event.
	 * @param source the {@link CyGroup} that has been added.
	 * @param network the {@link CyNetwork} the group has been added to.
	 */
	public GroupAddedToNetworkEvent(final CyGroup source, final CyNetwork network) {
		super(source, GroupAddedToNetworkListener.class, network);
	}
}

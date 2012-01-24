package org.cytoscape.group.events;


import org.cytoscape.group.CyGroupManager;
import org.cytoscape.group.CyGroup;
import org.cytoscape.model.CyNetwork;


/**
 * This event signals that a network has been added.
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

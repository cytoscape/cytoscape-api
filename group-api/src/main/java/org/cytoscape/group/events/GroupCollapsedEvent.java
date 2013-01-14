package org.cytoscape.group.events;


import org.cytoscape.group.CyGroup;

import org.cytoscape.model.CyNetwork;


/**
 * This event signals that a group has either been expanded or collapsed in
 * a particular network.
 *
 * @CyAPI.Final.Class
 * @CyAPI.InModule group-api
 */
public final class GroupCollapsedEvent extends AbstractGroupEvent {
	private boolean collapsed;

	/**
	 * Constructs event.
	 * @param source the {@link CyGroup} that has been changed.
	 * @param network the {@link CyNetwork} the group has been changed in.
	 * @param collapsed true if the group has collapsed
	 */
	public GroupCollapsedEvent(final CyGroup source, final CyNetwork network, boolean collapsed) {
		super(source, GroupCollapsedListener.class, network);
		this.collapsed = collapsed;
	}

	/**
	 * Is the group collapsed or expanded?
	 *
	 * @return true if the group was collapsed
	 */
	public boolean collapsed() {
		return collapsed;
	}
}

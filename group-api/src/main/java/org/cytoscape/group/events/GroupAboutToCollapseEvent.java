package org.cytoscape.group.events;


import org.cytoscape.group.CyGroup;

import org.cytoscape.model.CyNetwork;


/**
 * This event signals that a group is either about to be expanded or collapsed in
 * a particular network.
 *
 * @CyAPI.Final.Class
 * @CyAPI.InModule group-api
 */
public final class GroupAboutToCollapseEvent extends AbstractGroupEvent {
	private boolean collapsing;

	/**
	 * Constructs event.
	 * @param source the {@link CyGroup} that has been changed.
	 * @param network the {@link CyNetwork} the group has been changed in.
	 * @param collapsing true if the group is about to be collapsed
	 */
	public GroupAboutToCollapseEvent(final CyGroup source, final CyNetwork network, boolean collapsing) {
		super(source, GroupAboutToCollapseListener.class, network);
		this.collapsing = collapsing;
	}

	/**
	 * Is the group collapsed or expanded?
	 *
	 * @return true if the group is about to be collapsed
	 */
	public boolean collapsing() {
		return collapsing;
	}
}

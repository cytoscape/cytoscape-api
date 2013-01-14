package org.cytoscape.group.events;


import org.cytoscape.group.CyGroupManager;
import org.cytoscape.group.CyGroup;


/**
 * This event signals that a new group has been added.
 * @CyAPI.Final.Class
 * @CyAPI.InModule group-api
 */
public final class GroupAddedEvent extends AbstractGroupManagerEvent {
	/**
	 * Constructs event.
	 * @param source the {@link CyGroupManager} the group has been added to.
	 * @param group the {@link CyGroup} that has been added.
	 */
	public GroupAddedEvent(final CyGroupManager source, final CyGroup group) {
		super(source, GroupAddedListener.class, group);
	}
}

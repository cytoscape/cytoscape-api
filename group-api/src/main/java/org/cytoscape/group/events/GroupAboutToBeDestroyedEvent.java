package org.cytoscape.group.events;


import org.cytoscape.group.CyGroupManager;
import org.cytoscape.group.CyGroup;


/**
 * This event signals that a group is about to be destroyed -- i.e. completely removed
 * from all networks.  The group's subnetwork and group node will also be destroyed.
 *
 * @CyAPI.Final.Class
 * @CyAPI.InModule group-api
 */
public final class  GroupAboutToBeDestroyedEvent extends AbstractGroupManagerEvent {
	/**
	 * Constructs event.
	 * @param source the {@link CyGroupManager} of the group about to be destroyed.
	 * @param group the {@link CyGroup} about to be destroyed.
	 */
	public GroupAboutToBeDestroyedEvent(final CyGroupManager source, final CyGroup group) {
		super(source, GroupAboutToBeDestroyedListener.class, group);
	}
}

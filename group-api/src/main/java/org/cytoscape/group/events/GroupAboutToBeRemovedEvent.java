package org.cytoscape.group.events;


import org.cytoscape.group.CyGroupManager;
import org.cytoscape.group.CyGroup;
import org.cytoscape.model.CyNetwork;


/**
 * This event signals that a network has been added.
 * @CyAPI.Final.Class
 */
public final class GroupAboutToBeRemovedEvent extends AbstractGroupEvent {
	/**
	 * Constructs event.
	 * @param source the {@link CyGroup} that is about to be removed.
	 * @param network the {@link CyNetwork} the group is about to be removed from
	 */
	public GroupAboutToBeRemovedEvent(final CyGroup source, final CyNetwork network) {
		super(source, GroupAboutToBeRemovedListener.class, network);
	}
}

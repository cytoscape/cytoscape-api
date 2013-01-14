
package org.cytoscape.group.events;


import org.cytoscape.group.CyGroup;
import org.cytoscape.model.CyNode;
import java.util.Collections;
import java.util.List;


/**
 * This event signals that edges have been removed from the network.
 *
 * @CyAPI.Final.Class
 * @CyAPI.InModule group-api
 */
public final class GroupNodesRemovedEvent extends AbstractNodesEvent {

	/**
	 * Constructs event.
	 * @param source the {@link CyGroup} that is changing. 
	 * @param node A single node removed. 
	 */
	public GroupNodesRemovedEvent(final CyGroup source, CyNode node) {
		this(source, Collections.singletonList(node)); 
	}

	/**
	 * Constructs event.
	 * @param source the {@link CyGroup} that is changing. 
	 * @param nodes A list of nodes removed. 
	 */
	public GroupNodesRemovedEvent(final CyGroup source, List<CyNode> nodes) {
		super(source, nodes, GroupNodesRemovedListener.class);
	}
}

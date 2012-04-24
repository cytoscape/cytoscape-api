
package org.cytoscape.group.events;


import org.cytoscape.group.CyGroup;
import org.cytoscape.model.CyNode;
import java.util.Collections;
import java.util.List;


/**
 * This event signals that edges have been added to the network.
 *
 * @CyAPI.Final.Class
 */
public final class GroupNodesAddedEvent extends AbstractNodesEvent {

	/**
	 * Constructs event.
	 * @param source the {@link CyGroup} that is changing. 
	 * @param node A single node added. 
	 */
	public GroupNodesAddedEvent(final CyGroup source, CyNode node) {
		this(source, Collections.singletonList(node)); 
	}

	/**
	 * Constructs event.
	 * @param source the {@link CyGroup} that is changing. 
	 * @param nodes A list of nodes added. 
	 */
	public GroupNodesAddedEvent(final CyGroup source, List<CyNode> nodes) {
		super(source, nodes, GroupNodesAddedListener.class);
	}
}

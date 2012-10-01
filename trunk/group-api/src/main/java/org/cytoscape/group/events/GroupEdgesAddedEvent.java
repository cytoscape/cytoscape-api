package org.cytoscape.group.events;


import org.cytoscape.group.CyGroup;
import org.cytoscape.model.CyEdge;
import java.util.Collections;
import java.util.List;


/**
 * This event signals that edges have been added to the network.
 *
 * @CyAPI.Final.Class
 */
public final class GroupEdgesAddedEvent extends AbstractEdgesEvent {

	/**
	 * Constructs event.
	 * @param source the {@link CyGroup} that is changing. 
	 * @param edge A single edge added. 
	 */
	public GroupEdgesAddedEvent(final CyGroup source, CyEdge edge) {
		this(source, Collections.singletonList(edge));
	}

	/**
	 * Constructs event.
	 * @param source the {@link CyGroup} that is changing. 
	 * @param edges A list of edges added. May be null.
	 */
	public GroupEdgesAddedEvent(final CyGroup source, List<CyEdge> edges) {
		super(source, edges, GroupEdgesAddedListener.class);
	}
}

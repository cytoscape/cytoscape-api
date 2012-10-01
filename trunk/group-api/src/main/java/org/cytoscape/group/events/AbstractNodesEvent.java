
package org.cytoscape.group.events;


import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.group.CyGroup;
import org.cytoscape.model.CyNode;
import java.util.List; 
import java.util.Collections; 


abstract class AbstractNodesEvent extends AbstractCyEvent {

	private final List<CyNode> nodes;

	public AbstractNodesEvent(final CyGroup source, List<CyNode> nodes, Class<?> clazz) {
		super(source, clazz);
		if ( nodes == null )
			this.nodes = Collections.emptyList();	
		else
			this.nodes = nodes;
	}

	/**
	 * Returns the list of nodes associated with this event.  The list may
	 * be empty, but will not be null.
	 * @return the list of nodes associated with this event.  
	 */
	public List<CyNode> getNodes() {
		return nodes;
	}
}

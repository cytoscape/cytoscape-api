
package org.cytoscape.group.events;


import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.group.CyGroup;
import org.cytoscape.model.CyEdge;
import java.util.List; 
import java.util.Collections; 


abstract class AbstractEdgesEvent extends AbstractCyEvent {

	private final List<CyEdge> edges;

	public AbstractEdgesEvent(final CyGroup source, List<CyEdge> edges, Class<?> clazz) {
		super(source, clazz);
		if ( edges == null )
			this.edges = Collections.emptyList();	
		else
			this.edges = edges;
	}

	/**
	 * Returns the list of edges associated with this event.  The list may
	 * be empty, but will not be null.
	 * @return the list of edges associated with this event.  
	 */
	public List<CyEdge> getEdges() {
		return edges;
	}
}

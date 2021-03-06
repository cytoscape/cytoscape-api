package org.cytoscape.group.events;

/*
 * #%L
 * Cytoscape Groups API (group-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2021 The Cytoscape Consortium
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */


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

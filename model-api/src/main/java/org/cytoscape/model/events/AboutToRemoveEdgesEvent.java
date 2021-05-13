package org.cytoscape.model.events;

/*
 * #%L
 * Cytoscape Model API (model-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2021 The Cytoscape Consortium
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

import java.util.Collection;

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;


/**
 * Fired before an edge is actually removed so that listeners
 * have a chance to clean up before the edge object disappears.
 * @CyAPI.Final.Class
 * @CyAPI.InModule model-api
 */
public final class AboutToRemoveEdgesEvent extends AbstractCyEvent<CyNetwork> {
	
	private final Collection<CyEdge> edges;
	/**
	 * Constructs event.
	 * @param source the network firing this event.
	 * @param edges the collection of edges about to be removed. 
	 */
	public AboutToRemoveEdgesEvent(final CyNetwork source, final Collection<CyEdge> edges) {
		super(source, AboutToRemoveEdgesListener.class);
		if ( edges == null )
			throw new NullPointerException("edge collection may not be null");
		this.edges = edges;
	}
	
	/**
	 * Returns the collection of edges about to be removed.
	 * @return the collection of edges about to be removed.
	 */
	public Collection<CyEdge> getEdges() {
			return edges;
	}
 }

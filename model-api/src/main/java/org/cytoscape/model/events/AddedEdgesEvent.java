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

import org.cytoscape.event.AbstractCyPayloadEvent;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;


/**
 * An event fired when an edge is added to a network. 
 * @CyAPI.Final.Class
 * @CyAPI.InModule model-api
 */
public final class AddedEdgesEvent extends AbstractCyPayloadEvent<CyNetwork, CyEdge> {

	/**
	 * Constructs event.
	 * @param source The CyNetwork the edge was added to.
	 * @param edges The collection of CyEdge objects added to the network. 
	 */
	public AddedEdgesEvent(final CyNetwork source, final Collection<CyEdge> edges) {
		super(source, AddedEdgesListener.class, edges);
	}

}

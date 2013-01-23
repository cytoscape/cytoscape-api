package org.cytoscape.model.events;

/*
 * #%L
 * Cytoscape Model API (model-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2013 The Cytoscape Consortium
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
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;


/**
 * Fired before a node is actually removed so that listeners
 * have a chance to clean up before the node object disappears.
 * Note that this event also <i>implies</i> an AboutToRemoveEdgesEvent
 * for every edge adjacent to the node in question (because all
 * adjacent edges are removed as a consequence of removing
 * a node), even though the
 * AboutToRemoveEdgesEvent is not actually fired.  If you only care
 * about removing edges, be sure to listen for this event as well!
 * @CyAPI.Final.Class
 * @CyAPI.InModule model-api
 */
public final class AboutToRemoveNodesEvent extends AbstractCyEvent<CyNetwork> {
	
	private final Collection<CyNode> nodes;
	
	/**
	 * Constructs event.
	 * @param source the network firing this event.
	 * @param nodes the collection of nodes about to be removed. 
	 */
	public AboutToRemoveNodesEvent(final CyNetwork source, final Collection<CyNode> nodes) {
		super( source, AboutToRemoveNodesListener.class);
		if ( nodes == null )
			throw new NullPointerException("node collection may not be null");
		this.nodes = nodes;
	}
	
	/**
	 * Returns the collection of nodes about to be removed.
	 * @return the collection of nodes about to be removed.
	 */
	public Collection<CyNode> getNodes() {
			return nodes;
	}
}

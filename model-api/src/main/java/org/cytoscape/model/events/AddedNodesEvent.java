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

import org.cytoscape.event.AbstractCyPayloadEvent;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;


/**
 * Event for upper layers.  In general, network view models listen to this event
 * and creates view model for the node.  This will be fired to ALL network view models.
 * @CyAPI.Final.Class
 * @CyAPI.InModule model-api
 */
public final class AddedNodesEvent extends AbstractCyPayloadEvent<CyNetwork,CyNode> {

	/**
	 * Constructs event.
	 * @param source The CyNetwork the node was added to.
	 * @param nodes The collection of nodes that were added to the network.
	 */
	public AddedNodesEvent(final CyNetwork source, final Collection<CyNode> nodes) {
		super(source, AddedNodesListener.class, nodes);
	}
}

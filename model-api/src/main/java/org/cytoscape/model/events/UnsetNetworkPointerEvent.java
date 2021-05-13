package org.cytoscape.model.events;

/*
 * #%L
 * Cytoscape Model API (model-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2010 - 2021 The Cytoscape Consortium
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


import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;


/**
 * Used to track removing of nested networks from nodes. 
 * @CyAPI.Final.Class
 * @CyAPI.InModule model-api
 */
public final class UnsetNetworkPointerEvent extends AbstractNestedNetworkEvent {
	/**
	 * Constructs event.
	 * @param node     the node that the nested network was removed from
	 * @param network  the network that was removed as the nested network on "node"
	 */
	public UnsetNetworkPointerEvent(final CyNode node, final CyNetwork network) {
		super(UnsetNetworkPointerListener.class, node, network);
	}
}

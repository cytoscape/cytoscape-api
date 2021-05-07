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
 * Used to track setting of nested networks on nodes. 
 * @CyAPI.Final.Class
 * @CyAPI.InModule model-api
 */
public final class SetNetworkPointerEvent extends AbstractNestedNetworkEvent {
	/**
	 * Constructs event.
	 * @param node     the node that the nested network was set on
	 * @param network  the network that was set as the nested network on "node"
	 */
	public SetNetworkPointerEvent(final CyNode node, final CyNetwork network) {
		super(SetNetworkPointerListener.class, node, network);
	}
}

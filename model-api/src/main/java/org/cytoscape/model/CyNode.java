package org.cytoscape.model;

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


import org.cytoscape.model.events.SetNetworkPointerEvent;
import org.cytoscape.model.events.UnsetNetworkPointerEvent;


/**
 * An object that represents a node (vertex) within a network 
 * of nodes and edges.
 * @CyAPI.Api.Interface
 */
public interface CyNode extends CyIdentifiable {
	/**
	 * Returns the network referenced by this node.  If no 
	 * network has been specified this method will return null.
	 * 
	 * @return A reference to a CyNetwork if one exists, 
	 * returns null otherwise.
	 */
	CyNetwork getNetworkPointer();

	/**
	 * Allows a network reference for this node to be set. Only one
	 * network may be set for a given node. Any CyNetwork may
	 * be used (the network may be a subnetwork of the network that
	 * contains this node, but it is not mandatory). To unset a network, use
	 * null as an argument. 
	 *
	 * @param network The network that is to be referenced by this node. If
	 * this value is null, any existing reference will be removed.
	 *
	 * Note that this if a previous referenced network is being replaced or nulled out, an
	 * {@link UnsetNetworkPointerEvent} will be fired and if a new network will be set a
	 * {@link SetNetworkPointerEvent} will be fired.  
	 */
	void setNetworkPointer(CyNetwork network);
}

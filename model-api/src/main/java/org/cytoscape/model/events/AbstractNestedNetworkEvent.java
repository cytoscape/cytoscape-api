package org.cytoscape.model.events;

/*
 * #%L
 * Cytoscape Model API (model-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2010 - 2013 The Cytoscape Consortium
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

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;


/** Base class for all nested network events. */
class AbstractNestedNetworkEvent extends AbstractCyEvent<CyNode> {
	private final CyNode node;
	private final CyNetwork network;

	AbstractNestedNetworkEvent(final Class<?> listenerClass, final CyNode node, final CyNetwork network) {
		super(node, listenerClass);
		if (network == null)
			throw new NullPointerException("network cannot be null.");
		this.node = node;
		this.network = network;
	}

	/**
	 * Returns the CyNode for this event.
	 * @return The CyNode for this event.
	 */
	public CyNode getNode() {
		return node;
	}

	/**
	 * Returns the CyNetwork for this event.
	 * @return The CyNetwork for this event.
	 */
	public CyNetwork getNetwork() {
		return network;
	}
}

package org.cytoscape.model.events;

/*
 * #%L
 * Cytoscape Model API (model-api)
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
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNetworkManager;


/**
 *  Base class for all derived concrete event classes classes in this package that require a CyNetwork.
 */
class AbstractNetworkEvent extends AbstractCyEvent<CyNetworkManager> {
	private final CyNetwork net;

	AbstractNetworkEvent(final CyNetworkManager source, final Class<?> listenerClass, final CyNetwork net) {
		super(source, listenerClass);

		if (net == null)
			throw new NullPointerException("the \"net\" parameter must never be null.");
		this.net = net;
	}

	public final CyNetwork getNetwork() {
		return net;
	}
}

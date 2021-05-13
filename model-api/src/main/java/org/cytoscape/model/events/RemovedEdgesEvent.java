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

import org.cytoscape.event.AbstractCyEvent;

import org.cytoscape.model.CyNetwork;


/**
 * Just a signal in case anyone wants to 
 * resync after an edge has been removed.
 * @CyAPI.Final.Class
 * @CyAPI.InModule model-api
 */
public final class RemovedEdgesEvent extends AbstractCyEvent<CyNetwork> {
	/**
	 * Constructs event.
	 * @param source The network from which an edge was deleted.
	 */
	public RemovedEdgesEvent(final CyNetwork source) {
		super(source,RemovedEdgesListener.class);
	}
}

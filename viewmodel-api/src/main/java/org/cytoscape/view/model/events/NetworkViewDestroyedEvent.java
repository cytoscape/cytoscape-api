package org.cytoscape.view.model.events;

/*
 * #%L
 * Cytoscape View Model API (viewmodel-api)
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


import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.event.AbstractCyEvent;


/**
 * When a {@link CyNetworkView} is destroyed, this event will be fired.
 * @CyAPI.Final.Class
 * @CyAPI.InModule viewmodel-api
 */
public final class NetworkViewDestroyedEvent extends AbstractCyEvent<CyNetworkViewManager> {
	/**
	 * Creates the event for network views that are destroyed.
	 * 
	 * @param source the {@link CyNetworkViewManager} of the CyNetworkView that was destroyed.
	 */
	public NetworkViewDestroyedEvent(final CyNetworkViewManager source) {
		super(source, NetworkViewDestroyedListener.class);
	}
}

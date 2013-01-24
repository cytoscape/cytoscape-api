package org.cytoscape.view.model.events;

/*
 * #%L
 * Cytoscape View Model API (viewmodel-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
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


import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;


/**
 * When a {@link CyNetworkView} is added to a {@linkplain CyNetworkViewManager}, this event will be fired.
 * @CyAPI.Final.Class
 */
public final class NetworkViewAddedEvent extends AbstractNetworkViewEvent {
	/**
	 * Creates the event for network views that are added.
	 * 
	 * @param source the {@link CyNetworkViewManager} that the CyNetworkView was added to.
	 * @param view the {@link CyNetworkView} that was added.
	 */
	public NetworkViewAddedEvent(final CyNetworkViewManager source, final CyNetworkView view) {
		super(source, NetworkViewAddedListener.class, view);
	}
}

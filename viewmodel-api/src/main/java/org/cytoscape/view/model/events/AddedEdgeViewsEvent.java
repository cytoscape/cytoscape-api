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

import java.util.Collection;

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.event.AbstractCyPayloadEvent;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;
import org.cytoscape.model.CyEdge;

/**
 * When edge {@linkplain View} is added to a {@linkplain CyNetworkView}, this event will be fired.
 * @CyAPI.Final.Class
 * @CyAPI.InModule viewmodel-api
 */
public final class AddedEdgeViewsEvent extends AbstractCyPayloadEvent<CyNetworkView,View<CyEdge>> {
	
	
	/**
	 * Creates the event for new edge views.
	 * 
	 * @param source network view which includes the new edge view.
	 * @param edgeViews Collection of newly created view object for an edge.
	 * 
	 */
	public AddedEdgeViewsEvent(final CyNetworkView source, final Collection<View<CyEdge>> edgeViews) {
		super(source, AddedEdgeViewsListener.class, edgeViews);
	}

	/**
	 * Returns new edge view added to the source network view object.
	 * 
	 * @return new edge view added to the network view.
	 * 
	 */
	public Collection<View<CyEdge>> getEdgeViews() {
		return getPayloadCollection();
	}
}

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

import java.util.Collection;

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.event.AbstractCyPayloadEvent;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;
import org.cytoscape.model.CyNode;

/**
 * When node {@linkplain View} is added to a {@linkplain CyNetworkView}, this event will be fired.
 * @CyAPI.Final.Class
 */
public final class AddedNodeViewsEvent extends AbstractCyPayloadEvent<CyNetworkView,View<CyNode>> {


	/**
	 * Creates the event for a new node view.
	 * 
	 * @param source network view which includes the new node view.
	 * @param nodeViews Collection of newly created view object for a node.
	 * 
	 */
	public AddedNodeViewsEvent(final CyNetworkView source, final Collection<View<CyNode>> nodeViews) {
		super(source, AddedNodeViewsListener.class,nodeViews);
	}

	/**
	 * Returns new node view object.
	 * 
	 * @return new node view
	 */
	public Collection<View<CyNode>> getNodeViews() {
		return getPayloadCollection();
	}
}

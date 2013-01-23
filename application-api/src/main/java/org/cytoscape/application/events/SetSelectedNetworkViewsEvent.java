package org.cytoscape.application.events;

/*
 * #%L
 * Cytoscape Application API (application-api)
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

import java.util.List;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.view.model.CyNetworkView;


/**
 * An event that indicates that a collection of network views has been selected.
 * @CyAPI.Final.Class
 * @CyAPI.InModule application-api
 */
public final class SetSelectedNetworkViewsEvent extends AbstractCyEvent<CyApplicationManager> {
	private final List<CyNetworkView> views;

	/**
	 * Constructor.
	 * @param source The application manager firing the event.
	 * @param views A list of CyNetworkViews that have been selected. 
	 */
	public SetSelectedNetworkViewsEvent(final CyApplicationManager source, final List<CyNetworkView> views) {
		super(source, SetSelectedNetworkViewsListener.class);
		this.views = views;
	}

	/**
	 * Returns the list of network views that have been selected.
	 * @return The list of network views that have been selected.
	 */
	public List<CyNetworkView> getNetworkViews() {
		return views;
	}
}

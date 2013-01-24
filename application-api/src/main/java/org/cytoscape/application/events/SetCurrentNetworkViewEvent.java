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

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.view.model.CyNetworkView;


/**
 * An event indicating that a network view has been set to current.
 * @CyAPI.Final.Class
 */
public final class SetCurrentNetworkViewEvent extends AbstractCyEvent<CyApplicationManager> {


	private final CyNetworkView view;

	/**
	 * Returns the network view associated with this event. The view returned may be null!
	 * @return the network view associated with this event.
	 */
	public final CyNetworkView getNetworkView() {
		return view;
	}


	/**
	 * Constructor.
	 * @param source The application manager firing this event.
	 * @param view The network view that has been set as current.
	 */
	public SetCurrentNetworkViewEvent(final CyApplicationManager source, final CyNetworkView view) {
		super(source, SetCurrentNetworkViewListener.class);
		this.view = view;
	}
}

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


import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.view.model.CyNetworkView;


/**
 *  Base class for all derived concrete event classes classes in this package that require a {@link CyNetworkView}.
 *
 */
class AbstractNetworkViewEvent extends AbstractCyEvent<CyNetworkViewManager> {
	private final CyNetworkView view;

	AbstractNetworkViewEvent(final CyNetworkViewManager source, final Class<?> listenerClass, final CyNetworkView view) {
		super(source, listenerClass);

		if (view == null)
			throw new NullPointerException("the \"view\" parameter must never be null.");
		this.view = view;
	}

	public final CyNetworkView getNetworkView() {
		return view;
	}
}

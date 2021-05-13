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
import org.cytoscape.view.model.CyNetworkView;

/**
 * Fit network visualization to the given container. 
 * <p>
 * This event should
 * be created and fired by the view being updated and not anyone else,
 * instead call the {@link CyNetworkView#fitContent()} method.
 * @CyAPI.Final.Class
 * @CyAPI.InModule viewmodel-api
 */
public final class FitContentEvent extends AbstractCyEvent<CyNetworkView> {
	
	/**
	 * Fit the size of network view to the current container.
     * <p>
     * This event should
     * be created and fired by the view being updated and not anyone else,
     * instead call the {@link CyNetworkView#fitContent()} method.
	 * 
	 * @param source network view to be fitted to the container.
	 */
	public FitContentEvent(final CyNetworkView source) {
		super(source, FitContentListener.class);
	}
}

package org.cytoscape.view.presentation.property.values;

/*
 * #%L
 * Cytoscape Presentation API (presentation-api)
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

import org.cytoscape.model.CyEdge;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;


/**
 * Factory of the handle object.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule presentation-api
 */
public interface HandleFactory {
	
	/**
	 * Creates a new instance of the Handle.
	 * 
	 * @param x x position of the handle (absolute position)
	 * @param y y position of the handle (absolute position)
	 * 
	 * @return instance of new handle.
	 */
	Handle createHandle(final CyNetworkView graphView, final View<CyEdge> view, double x, double y);
}

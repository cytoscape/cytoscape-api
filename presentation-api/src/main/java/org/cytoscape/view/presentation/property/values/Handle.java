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

import java.awt.geom.Point2D;

import org.cytoscape.view.model.View;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.model.CyEdge;


/**
 * Defines a handle, an anchor point in the edge. 
 * @CyAPI.Api.Interface
 * @CyAPI.InModule presentation-api
 */
public interface Handle {

	/**
	 * Calculate absolute position of this handle for the given edge view.
	 * 
	 * @param netView graph view for this handle
	 * @param edgeView The handle belongs to this edge view.
	 * 
	 * @return Absolute position of this handle in the network view.
	 */
	Point2D calculateHandleLocation(final CyNetworkView netView, final View<CyEdge> edgeView);
	
	/**
	 * Define this handle.  Handle will be described as a relative location from source and target node.
	 * 
	 * NOTE: in the implementation, the given (x, y) values will be converted to relative position.
	 * The conversion equation is exchangeable.
	 * 
	 * @param netView graph view for this handle
	 * @param edgeView The handle belongs to this edge view.
	 * @param x Absolute value for X-location.
	 * @param y Absolute value for Y-location.
	 */
	void defineHandle(final CyNetworkView netView, final View<CyEdge> edgeView, final double x, final double y);
	
	/**
	 * Create string representation of this object for parsing.
	 * 
	 * @return String representation of this Handle.
	 */
	String getSerializableString();
}

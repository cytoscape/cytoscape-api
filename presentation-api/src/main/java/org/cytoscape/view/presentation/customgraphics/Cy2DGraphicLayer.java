package org.cytoscape.view.presentation.customgraphics;

import java.awt.Graphics2D;
import java.awt.Shape;

import org.cytoscape.model.CyColumn;
import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.model.CyRow;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;
import org.cytoscape.view.model.table.CyTableView;

/*
 * #%L
 * Cytoscape Presentation API (presentation-api)
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

/**
 * Custom graphic layer interface that allows a custom graphics implementation to draw directly onto the
 * {@link CyNetworkView}'s {@link Graphics2D} object.
 * @see CyCustomGraphics CyCustomGraphics2
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule presentation-api
 */
public interface Cy2DGraphicLayer extends CustomGraphicLayer {

	/**
	 * This method is called by Cytoscape when a {@link CyNetworkView} is being updated in order to let the 
	 * custom graphics draw onto its {@link Graphics2D} object.
	 * @param g The graphics object to draw onto
	 * @param shape The node view's shape or the edge or network view's bounding box
	 * @param networkView The network view being updated
	 * @param view The view object that has this layer's {@link CyCustomGraphics} as a visual property value
	 *        (so far, only CyNode views are supported by Cytoscape's default 
	 *        {@link org.cytoscape.view.presentation.RenderingEngine})
	 */
	void draw(Graphics2D g, Shape shape, CyNetworkView networkView, View<? extends CyIdentifiable> view);
	
	/**
	 * This method is called by Cytoscape when a {@link CyTableView} is being updated in order to let the 
	 * custom graphics draw onto its cells.
	 * @param g
	 * @param networkView The table view being updated
	 * @param column
	 * @param row
	 */
	default void draw(Graphics2D g, CyTableView tableView, CyColumn column, CyRow row) {
		// To be implemented by the implementation class...
	}
}

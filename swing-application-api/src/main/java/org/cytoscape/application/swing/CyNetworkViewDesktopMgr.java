package org.cytoscape.application.swing;

/*
 * #%L
 * Cytoscape Swing Application API (swing-application-api)
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

import java.awt.Dimension;
import java.awt.Rectangle;

import org.cytoscape.view.model.CyNetworkView;

/**
 * Interface for managing CyNetworkViews within the desktop where they
 * reside.
 */
public interface CyNetworkViewDesktopMgr {
	public static enum ArrangeType {CASCADE, HORIZONTAL, VERTICAL, GRID};
	/**
	 * Return the size of the desktop view area where all CyNetworkViews
	 * reside.
	 * @return Dimension of the width x height (pixels) of the desktop view
	 *                  area.
	 */
	public Dimension getDesktopViewAreaSize();

	/**
	 * Get the existing bounds of a given CyNetworkView within the desktop
	 * view area.
	 * @param view the CyNetworkView for which to obtain the bounds.
	 * @return a Rectangle whose x,y position is the upper left corner of
	 *         view and whose width and height are the dimensions of 
	 *         view.
	 */
	public Rectangle getBounds (CyNetworkView view);

	/**
	 * Set the bounds of a given CyNetworkView within the desktop
	 * view area.
	 * @param view the CyNetworkView for which to set the bounds.
	 * @param bounds a Rectangle whose x,y position is the desired
	 *        upper left corner of view and whose width and height are
	 *        the desired dimensions of view. 
	 */
	public void setBounds (CyNetworkView view, Rectangle bounds);

	/**
	 * Request the desktop view manager to tile (or untile) the current
	 * network windows.
	 *
	 * @param arrangeType the type of tiling to use.  A ArrangeTYpe of CASCADE
	 *        will revert to cascade (i.e. non-tiled) windows
	 */
	public void arrangeWindows(ArrangeType arrangeType);
}

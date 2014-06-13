package org.cytoscape.view.presentation.customgraphics;

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

import java.awt.Paint;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

/**
 * This interface defines a the mimimum interface to
 * add a custom graphics to a {@link org.cytoscape.model.CyNode}.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule presentation-api
 */
public interface CustomGraphicLayer {
	/**
 	 * Return the bounds of the area covered by this
 	 * CustomGraphicLayer as a {@link java.awt.Rectangle2D}
 	 *
 	 * @return the {@link java.awt.Rectangle2D}
 	 */
	public Rectangle2D getBounds2D();

	/**
 	 * Return the {@link java.awt.Paint} to be used to
 	 * fill the {@link java.awt.Shape}.
 	 *
 	 * @param bounds the bounding box of the {@link org.cytoscape.model.CyNode}.
 	 * @return the fill {@link java.awt.Paint}
 	 */
	public Paint getPaint(Rectangle2D bounds);

	/**
 	 * Return a new CustomGraphicLayer that has been transformed by the
 	 * provided {@link java.awt.geom.AffineTransform}.
 	 *
 	 * @param xform transform to perform on the shape
 	 * @return the transformed CustomGraphicLayer
 	 */
  public CustomGraphicLayer transform(AffineTransform xform);
}

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
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;

/**
 * This interface defines a the information required to
 * construct a {@link java.awt.Shape} that has an arbitrary
 * fill {@link java.awt.Paint} and {@link java.awt.Stroke}.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule presentation-api
 */
public interface PaintedShape extends CustomGraphicLayer {
	/**
 	 * Return the {@link java.awt.Shape}
 	 *
 	 * @return the {@link java.awt.Shape}
 	 */
	public Shape getShape();

	/**
 	 * Return the {@link java.awt.Paint} to fill the shape with.  This
 	 * is a convenience method that is essentially the same as calling
 	 * getPaint(getShape().getBounds());
 	 *
 	 * @return the {@link java.awt.Paint}
 	 */
	public Paint getPaint();

	/**
 	 * Return the {@link java.awt.Stroke} to use to outline the
 	 * {@link java.awt.Shape} provided by the {@link #getShape} method above.
 	 *
 	 * @return the {@link java.awt.Stroke} to use.
 	 */
	public Stroke getStroke();

	/**
 	 * Return the {@link java.awt.Paint} to use to color the
 	 * {@link java.awt.Stroke} returned by {@link #getStroke}.
 	 *
 	 * @return the stroke {@link java.awt.Paint}
 	 */
	public Paint getStrokePaint();
}

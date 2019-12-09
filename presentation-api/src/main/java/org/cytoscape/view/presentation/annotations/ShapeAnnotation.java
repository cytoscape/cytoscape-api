package org.cytoscape.view.presentation.annotations;

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

import java.util.List;


/**
 * A Shape annotation supports annotations that can be drawn and filled
 * on the graphics canvas.  Shape annotations can also be resized and
 * have separate colors for border and fill.
 *
 * @CyAPI.Api.Interface
 * @CyAPI.InModule presentation-api
 */
public interface ShapeAnnotation extends Annotation {
	public static enum ShapeType {
		RECTANGLE ("Rectangle"),
		ROUNDEDRECTANGLE ("Rounded Rectangle"),
		ELLIPSE ("Ellipse"),
		TRIANGLE ("Triangle"),
		PENTAGON ("Pentagon"),
		STAR5 ("5-Pointed Star"),
		HEXAGON ("Hexagon"),
		STAR6 ("6-Pointed Star"),
		OCTAGON ("Octagon"),
		PARALLELOGRAM ("Parallelogram"),
		V("V"),
		DIAMOND("Diamond"),
		CUSTOM ("Custom");
	
		private final String name;
		ShapeType (String name) { 			this.name = name; 	}
	
		public String shapeName() {			return name;		}

		public String toString() {			return name;		}
	} 
	
	/**
	 * The width of the shape
	 */
	public static final String WIDTH="width";

	/**
	 * The height of the shape
	 */
  public static final String HEIGHT="height";

	/**
	 * The color of the border
	 */
  public static final String EDGECOLOR = "edgeColor";

	/**
	 * The thickness of the border
	 */
  public static final String EDGETHICKNESS = "edgeThickness";

	/**
	 * The opacity of the border
	 */
  public static final String EDGEOPACITY = "edgeOpacity";

	/**
	 * The fill color of the shape
	 */
  public static final String FILLCOLOR = "fillColor";

	/**
	 * The opacity of the shape fill
	 */
  public static final String FILLOPACITY = "fillOpacity";

	/**
	 * The shape type
	 */
  public static final String SHAPETYPE = "shapeType";

	/**
	 * A flag that this is a "custom" shape
	 */
  public static final String CUSTOMSHAPE = "customShape";

	/**
	 * Get the list of supported shapes.  If a shape is not
	 * supported, asking for it will result in nothing being
	 * displayed on the canvas.  This returns a string to
	 * facilitate the future addition of new shapes.
	 *
	 * @return list of supported shapes.
	 */
	public List<String> getSupportedShapes();

	/**
	 * Set the size of the shape.
	 *
	 * @param width the shape width
	 * @param height the shape height
	 */
	public void setSize(double width, double height);

	/**
	 * Get the current shape type as a String
	 *
	 * @return the current shape type
	 */
	public String getShapeType();

	/**
	 * Set the current shape type
	 *
	 * @param type the shape type
	 */
	public void setShapeType(String type);

	/**
	 * Get the border (stroke) width.
	 *
	 * @return the border width
	 */
	public double getBorderWidth();

	/**
	 * Set the border (stroke) width.
	 *
	 * @param width the border width
	 */
	public void setBorderWidth(double width);

	/**
	 * Get the border (stroke) color.
	 *
	 * @return the border color
	 */
	public Paint getBorderColor();

	/**
	 * Get the border (stroke) opacity.
	 *
	 * @return the border opacity
	 */
	public double getBorderOpacity();

	/**
	 * Get the fill color.
	 *
	 * @return the fill color
	 */
	public Paint getFillColor();

	/**
	 * Return the fill opacity, a value between 0.0 (fully transparent) and 100.0 (fully opaque).
	 */
	public double getFillOpacity();

	/**
	 * Set the border (stroke) color.
	 *
	 * @param border the border color
	 */
	public void setBorderColor(Paint border);

	/**
	 * Set the border (stroke) opacity.
	 *
	 * @param opacity the border opacity
	 */
	public void setBorderOpacity(double opacity);

	/**
	 * Set the fill color.
	 *
	 * @param fill the fill color
	 */
	public void setFillColor(Paint fill);

	/**
	 * Set the fill opacity, a value between 0.0 (fully transparent) and 100.0 (fully opaque).
	 */
	public void setFillOpacity(final double opacity);

	/**
	 * Set a custom shape to be drawn.  This is 
	 * optional functionality that will only be
	 * funcional when "Custom" is in the list
	 * of supported shapes.  If custom shapes
	 * are supported, setting this parameter
	 * will force the shape type to "Custom".  If
	 * they are not supported, this method is
	 * implemented, but ignored.
	 *
	 * @param shape the custom shape to be drawn
	 */
	public void setCustomShape(Shape shape);

	/**
	 * Get the current shape as a {@link java.awt.Shape} object
	 *
	 * @return the current shape
	 */
	public Shape getShape();
}

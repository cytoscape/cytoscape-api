package org.cytoscape.view.presentation.annotations;

/*
 * #%L
 * Cytoscape Ding View/Presentation Impl (ding-presentation-impl)
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
 *
 * @CyAPI.Api.Interface
 * @CyAPI.InModule presentation-api
 */
public interface ShapeAnnotation extends Annotation {
	// These two methods provide a way to get the shapes
	// supported by this implementation
	public List<String> getSupportedShapes();

	public void setSize(double width, double height);

	public String getShapeType();
	public void setShapeType(String type);

	public double getBorderWidth();
	public void setBorderWidth(double width);

	public Paint getBorderColor();
	public Paint getFillColor();

	public void setBorderColor(Paint border);
	public void setFillColor(Paint fill);

	// public void setShape(Shape shape);
	public Shape getShape();
}

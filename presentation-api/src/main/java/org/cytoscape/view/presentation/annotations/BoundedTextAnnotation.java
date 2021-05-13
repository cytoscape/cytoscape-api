package org.cytoscape.view.presentation.annotations;

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

import java.awt.Color;
import java.awt.Font;

// NOTE: all implementations should implement TextAnnotation also
/**
 *
 * The BoundedText annotation is a ShapeAnnotation that also implements
 * Text. The provided text is positioned in the center of the shape
 * provided by the shape annotation.
 *
 * @CyAPI.Api.Interface
 * @CyAPI.InModule presentation-api
 */
public interface BoundedTextAnnotation extends ShapeAnnotation {

	/**
	 * This method causes the shape to be resized to fit the
	 * provided text.
	 */
	public void fitShapeToText();

	/**
	 * Set the text to be displayed as part of the annotation
	 *
	 * @param text the text annotation
	 */
	public void setText(String text);

	/**
	 * Get the text to be displayed as part of the annotation
	 *
	 * @return the text annotation
	 */
	public String getText();

	/**
	 * Set the color of the text to be displayed
	 *
	 * @param color the text color
	 */
	public void setTextColor(Color color);

	/**
	 * Get the color of the text to be displayed
	 *
	 * @return the text annotation
	 */
	public Color getTextColor();

	/**
	 * Set the size of the font used to display the text
	 *
	 * @param size the size of the font 
	 */
	public void setFontSize(double size);

	/**
	 * Get the size of the font used to display the text
	 *
	 * @return the font size
	 */
	public double getFontSize();

	/**
	 * Set the font style
	 *
	 * @param style the font style
	 */
	public void setFontStyle(int style);

	/**
	 * Get the font style
	 *
	 * @return the font style
	 */
	public int getFontStyle();

	/**
	 * Set the font family to be used for the text display
	 *
	 * @param family the font family to use
	 */
	public void setFontFamily(String family);

	/**
	 * Get the font family to be used for the text display
	 *
	 * @return the font family to use
	 */
	public String getFontFamily();

	/**
	 * Get the font being used
	 *
	 * @return the font being used
	 */
	public Font getFont();

	/**
	 * Set the font to be used.  Note that this will override the existing settings
	 * for font style, size, and family
	 *
	 * @param font the text font to use
	 */
	public void setFont(Font font);
}

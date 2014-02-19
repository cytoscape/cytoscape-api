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

import java.awt.Image;
import java.net.URL;

/**
 * This annotation positions an image on on the screen.
 * In the current implementation, the image is always rectangular,
 * so the {@link ShapeAnnotation#getSupportedShapes method will always 
 * return a list with a single value: "Rectangle".  In the future,
 * this might be expanded to support clipping to a shape.
 *
 * @CyAPI.Api.Interface
 * @CyAPI.InModule presentation-api
 */
public interface ImageAnnotation extends ShapeAnnotation {

	/**
	 * Return the image currently assigned to this annotation
	 * 
	 * @return the current image
	 */
	public Image getImage();

	/**
	 * Set the image to be displayed as part of this annotation
	 *
	 * @param image the image to be displayed
	 */
	public void setImage(Image image);

	/**
	 * Get the {@link URL} for the image to be displayed
	 *
	 * @param the image url
	 */
	public URL getImageURL();

	/**
	 * Set a {@link URL} to fetch the image from.
	 *
	 * @param url the {@link URL} of the image to be displayed
	 */
	public void setImage(URL url);

	/**
	 * Set the opacity of the image.  Opacity is a float value
	 * between 0.0 and 1.0 where 1.0 is completely opaque and
	 * 0.0 is completely transparent.
	 *
	 * @param opacity the opacity of the image
	 */
	public void setImageOpacity(float opacity);

	/**
	 * Return the current opacity of the image.
	 *
	 * @return the image opacity
	 */
	public float getImageOpacity();

	/**
	 * Set the brightness adjustment for the image.  Brightness is a percentage
	 * value from -100 to 100.
	 *
	 * @param brightness the brightness adjustment for the image
	 */
	public void setImageBrightness(int brightness);

	/**
	 * Return the brightness adjustment for this image.
	 *
	 * @return the brightness adjustment
	 */
	public int getImageBrightness();

	/**
	 * Set the contrast adjustment for the image.  Contrast is a non-linear adjustment
	 * with values from -100 to 100.
	 *
	 * @param contrast the contrast adjustment for the image
	 */
	public void setImageContrast(int contrast);

	/**
	 * Return the contrast adjustment for this image
	 *
	 * @return contrast adjustment
	 */
	public int getImageContrast();
}

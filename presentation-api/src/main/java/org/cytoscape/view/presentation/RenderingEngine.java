package org.cytoscape.view.presentation;

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


import java.awt.Graphics;
import java.awt.Image;
import java.awt.print.Printable;
import java.util.Map;
import java.util.Properties;

import javax.swing.Icon;

import org.cytoscape.model.CyDisposable;
import org.cytoscape.view.model.View;
import org.cytoscape.view.model.VisualLexicon;
import org.cytoscape.view.model.VisualProperty;

/**
 * RenderingEngine is an interface for all visualizers which renders. For a
 * given view-model it renders actual view on display, documents, etc.
 * 
 * @param <T>
 *            source data object to be visualized. For now we have only one
 *            implementation for {@link org.cytoscape.model.CyNetwork}, but it
 *            can be anything, including {@link org.cytoscape.model.CyTable}.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule presentation-api
 */
public interface RenderingEngine<T> extends CyDisposable {
	
	/**
	 * Returns {@linkplain View} being rendered.
	 * 
	 * @return view model.  This is an immutable object.
	 */
	View<T> getViewModel();

	
	/**
	 * Provide all compatible Visual Properties as a {@linkplain VisualLexicon}.
	 * 
	 * @return Visual Lexicon of this rendering engine.
	 */
	VisualLexicon getVisualLexicon();
	

	/**
	 * Get property values for the rendering engine, like LOD.
	 * Users can set each property using the setProperty method in {@linkplain Properties}.
	 * 
	 * @return property values.
	 * @deprecated Use {@link RenderingEngine#printCanvas(Graphics, Map)} instead.
	 */
	@Deprecated
	default Properties getProperties() {
		return new Properties();
	}
	
	
	/**
	 * For export image function.
	 * 
	 * @return A Printable object suitable for submission to a printer.
	 */
	Printable createPrintable();
	

	/**
	 * Render an {@linkplain Image} object from current visualization.
	 * 
	 * <p>
	 * Note: If you would like to create an thumbnail image of a network for display
	 * in the UI then the {@link NetworkImageFactory} service provides a more lightweight and
	 * simpler API for that purpose.
	 * </p>
	 * 
	 * @param width width of the image
	 * @param height height of the image
	 * 
	 * @return Image object created from current window.
	 */
	public Image createImage(int width, int height);
	

	/**
	 * Create {@link Icon} object for the given VisualProperty value.
	 * 
	 * @param <V> Data type, such as Color, String, Double, etc.
	 * 
	 * @param vp VisualProperty to be rendered as Icon.
	 * @param value Value for the Icon.  For example, if V is shape, this can be rectangle, triangle, and so on.
	 * @param width width of the icon
	 * @param height height of the icon
	 * 
	 * @return Icon rendered by this engine.
	 */
	<V> Icon createIcon(VisualProperty<V> vp, V value, int width, int height);

	/**
	 * Render presentation on the given Java 2D Canvas.
	 * 
	 * <p>
	 * Note: If you would like to create an thumbnail image of a network for display
	 * in the UI then the {@link NetworkImageFactory} service provides a more lightweight and
	 * simpler API for that purpose.
	 * </p>
	 * 
	 * @param printCanvas Graphics object provided by vector
	 */
	void printCanvas(Graphics printCanvas);
	
	/**
	 * Render presentation on the given Java 2D Canvas.
	 * 
	 * <p>
	 * Note: If you would like to create an thumbnail image of a network for display
	 * in the UI then the {@link NetworkImageFactory} service provides a more lightweight and
	 * simpler API for that purpose.
	 * </p>
	 * 
	 * @param printCanvas Graphics object provided by vector
	 * @param properties Used to configure the rendering output.
	 */
	default void printCanvas(Graphics printCanvas, Map<String,String> properties) {
		printCanvas(printCanvas);
	}
	
	/**
	 * This method must return the same ID which is returned by the associated
	 * implementation of NetworkViewRenderer.
	 * @return Must return the same value as NetworkViewRenderer.getId.
	 */
	String getRendererId();
}
